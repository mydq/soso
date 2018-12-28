package com.kettle.soso.restful.interfaces;

import com.kettle.soso.common.exceptions.BaseException;
import com.kettle.soso.common.exceptions.ProcessNotExistException;
import com.kettle.soso.common.model.FileDataModel;
import com.kettle.soso.common.model.KettleModel;
import com.kettle.soso.common.model.RequestDto;
import com.kettle.soso.common.model.ReturnResult;
import com.kettle.soso.common.utils.BuildCommandUtil;
import com.kettle.soso.common.utils.CommandUtil;
import com.kettle.soso.common.utils.FileBean;
import com.kettle.soso.common.utils.QRCodeUtil;
import com.kettle.soso.mybatis.dal.bo.CreditDataTypeBo;
import com.kettle.soso.mybatis.dal.model.CreditDataType;
import com.kettle.soso.mybatis.dal.model.CreditDataTypeExample;
import com.kettle.soso.mybatis.dal.model.CreditFile;
import com.kettle.soso.common.dto.UploadFileDto;
import com.kettle.soso.service.files.FileAndJobService;
import com.kettle.soso.task.interfaces.SchedulerJobInterface;
import lombok.Cleanup;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 文件操作与job交互
 * @Author: csz
 * @Date: 2018/12/18 20:24
 */
@Slf4j
@RestController
@RequestMapping("fileAndJob")
public class JobInterface {
    @Autowired
    Environment environment;
    @Autowired
    FileAndJobService fileAndJobService;
    @Autowired
    CreditDataTypeBo creditDataTypeBo;
    @Value("${document.path}")
    String documentPath;
    @Value("${kettle.path}")
    String kettlePath;
    @Autowired
    SchedulerJobInterface schedulerJobInterface;


    /**
     * 开启数据加载流程
     * @param requestDto
     * @return
     */
    @RequestMapping(value = "/startProcess", method = RequestMethod.POST)
    public ReturnResult startProcess(@RequestBody RequestDto<UploadFileDto> requestDto){
        log.info("JobInterface startProcess requestDto = {}",requestDto);
        UploadFileDto uploadFileDto = requestDto.getData();
        if (null == uploadFileDto){
            log.warn("JobInterface startProcess requestDto data is empty");
            return new ReturnResult(null, "fail","500","data is empty");
        }
        ReturnResult<String> returnResult;
        try {
            FileDataModel fileDataModel = uploadFileDto.getFile();
            CreditFile creditFile = new CreditFile();
            creditFile.setUuid(fileDataModel.getUuid());
            creditFile.setFileSuffix(FilenameUtils.getExtension(fileDataModel.getOriginalFilename()));
            creditFile.setFilePath(fileDataModel.getFilePath());
            creditFile.setFileOldName(FilenameUtils.getBaseName(fileDataModel.getOriginalFilename()));
            creditFile.setFileType(fileDataModel.getContentType());
            creditFile.setVerifyMd5(fileDataModel.getMd5());
            creditFile.setSize((int)fileDataModel.getSize());
            fileAndJobService.addFile(uploadFileDto, creditFile);
            verifyRunWay(uploadFileDto, creditFile.getFilePath(), creditFile.getUuid());
            returnResult = new ReturnResult<>();
            returnResult.setResult(creditFile.getUuid());
        }catch (BaseException e){
            log.warn("JobInterface startProcess error e = ",e);
            returnResult = new ReturnResult<>(null, "fail", e.code, e.getMessage());
        } catch (Exception e) {
            log.warn("JobInterface startProcess error e = ",e);
            returnResult = new ReturnResult<>(null,"fail","500",e.getMessage(),"系统异常");
        }
        return returnResult;
    }

    /**
     * 文件上传接口
     * @param multipartFile
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ReturnResult<FileDataModel> upload(@RequestParam("file") MultipartFile multipartFile){
        log.info("JobInterface upload start");
        ReturnResult<FileDataModel> returnResult;
        try {
            String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
            String uuid = UUID.randomUUID().toString();
            FileDataModel fileDataModel = FileDataModel.builder().contentType(multipartFile.getContentType())
                    .originalFilename(multipartFile.getOriginalFilename())
                    .size(multipartFile.getSize())
                    .md5(DigestUtils.md5DigestAsHex(multipartFile.getInputStream()))
                    .uuid(uuid)
                    .filePath(StringUtils.join(FileBean.notExistsToCreateDir(StringUtils.join(FileBean.notExistsToCreateDir(documentPath),"/",new SimpleDateFormat("yyyy-MM-dd").format(new Date()))), "/", uuid, ".", extension))
                    .build();
            multipartFile.transferTo(new File(fileDataModel.getFilePath()));
            returnResult = new ReturnResult<>(fileDataModel);
        }catch (Exception e){
            log.warn("JobInterface upload fail message = ",e);
            returnResult = new ReturnResult<>(null, "fail", "500", e.getMessage() ,"文件上传失败");
        }
        return returnResult;
    }


    /**
     * 响应错误文件的二维码
     * @param code
     * @param response
     */
    @RequestMapping(value = "/getQRCode",method = RequestMethod.GET)
    public void getQRCode(String code,HttpServletResponse response){
        log.info("JobInterface getQRCode code = {}",code);
        if (StringUtils.isBlank(code)){
            log.warn("JobInterface getQRCode code is empty");
        }
        try {
            String qrPath = StringUtils.join(environment.getProperty("access.address"), environment.getProperty("kettle.repository.value.three"), code, ".txt");
            response.setContentType("image/jpeg");
            @Cleanup ServletOutputStream outputStream = response.getOutputStream();
            QRCodeUtil.encode(qrPath, outputStream);
        }catch (Exception e){
            log.warn("JobInterface getQRCode error = ",e);
        }
    }


    /**
     * 判断运行kettle的方式，定时，还是立即执行
     * @param uploadFileDto
     */
    public void verifyRunWay(UploadFileDto uploadFileDto, String filepath, String uuid){
        String command = buildCommand(uploadFileDto, filepath, uuid);
        log.info("JobInterface verifyRunWay uuid = {} command = {}",uuid,command);
        if (StringUtils.isBlank(uploadFileDto.getExpression())){
            CommandUtil.runLinuxPrint(command, false);
        }else {
            schedulerJobInterface.addKettleSchedulerJob(uploadFileDto.getDataCode(), command, uploadFileDto.getExpression(), false);
        }
    }

    /**
     * 构建命令
     * @param uploadFileDto
     * @param filepath
     * @return
     */
    public String buildCommand(UploadFileDto uploadFileDto, String filepath, String uuid){
        CreditDataType creditDataType = creditDataTypeBo.selectDataProcess(uploadFileDto.getDataCode()).orElseThrow(ProcessNotExistException::new);
        KettleModel kettleModel = new KettleModel();
        kettleModel.setRep(environment.getProperty("kettle.repository.rep"));
        kettleModel.setUser(environment.getProperty("kettle.repository.user"));
        kettleModel.setPass(environment.getProperty("kettle.repository.pass"));
        kettleModel.setDir(environment.getProperty("kettle.repository.dir"));
        kettleModel.setJob(creditDataType.getDataProcess());
        Map<String, String> param = kettleModel.getParam();
        param.put(environment.getProperty("kettle.repository.param.two"), creditDataType.getDefaultTransfor());
        param.put(environment.getProperty("kettle.repository.param.one"), filepath);
        param.put(environment.getProperty("kettle.repository.param.three"), environment.getProperty("kettle.repository.value.three")+uuid);
        param.put(environment.getProperty("kettle.repository.param.four"), uploadFileDto.getOrganizationCode());
        param.put(environment.getProperty("kettle.repository.param.five"), uploadFileDto.getDataCode());
        return BuildCommandUtil.buildKitchenLinux(kettlePath, kettleModel, StringUtils.join(environment.getProperty("kettle.log"), uuid ,".log"));
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ReturnResult test(UploadFileDto uploadFileDto){
        try {
            CreditDataTypeExample creditDataTypeExample = new CreditDataTypeExample();
            creditDataTypeExample.createCriteria().andIsDeletedEqualTo("n");
            List<CreditDataType> creditDataTypes = creditDataTypeBo.selectByExample(creditDataTypeExample);
            creditDataTypes.forEach(System.out::println);
            System.out.println("success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ReturnResult();
    }

}
