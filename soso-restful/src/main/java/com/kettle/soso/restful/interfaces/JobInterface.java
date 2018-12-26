package com.kettle.soso.restful.interfaces;

import com.kettle.soso.common.exceptions.BaseException;
import com.kettle.soso.common.exceptions.ProcessNotExistException;
import com.kettle.soso.common.model.KettleModel;
import com.kettle.soso.common.model.ReturnResult;
import com.kettle.soso.common.utils.BuildCommandUtil;
import com.kettle.soso.common.utils.CommandUtil;
import com.kettle.soso.common.utils.FileBean;
import com.kettle.soso.mybatis.dal.bo.CreditDataTypeBo;
import com.kettle.soso.mybatis.dal.model.CreditDataType;
import com.kettle.soso.mybatis.dal.model.CreditDataTypeExample;
import com.kettle.soso.mybatis.dal.model.CreditFile;
import com.kettle.soso.common.dto.UploadFileDto;
import com.kettle.soso.service.files.FileAndJobService;
import com.kettle.soso.task.interfaces.SchedulerJobInterface;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 文件操作与job交互
 * @Author: csz
 * @Date: 2018/12/18 20:24
 */
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
     * 文件上传
     * @param uploadFileDto
     * @param multipartFile
     * @return
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public ReturnResult uploadFile(UploadFileDto uploadFileDto, @RequestParam("file") MultipartFile multipartFile){
        ReturnResult<String> returnResult = null;
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            CreditFile creditFile = new CreditFile();
            creditFile.setUuid(UUID.randomUUID().toString());
            creditFile.setFileSuffix(FilenameUtils.getExtension(originalFilename));
            creditFile.setFilePath(StringUtils.join(FileBean.notExistsToCreateDir(StringUtils.join(documentPath, "/", uploadFileDto.getOrganizationCode())),
                    "/", uploadFileDto.getDataCode(), "-", creditFile.getUuid(), ".", creditFile.getFileSuffix()));
            creditFile.setFileOldName(FilenameUtils.getBaseName(originalFilename));
            creditFile.setFileType(multipartFile.getContentType());
            creditFile.setVerifyMd5(DigestUtils.md5DigestAsHex(multipartFile.getInputStream()));
            creditFile.setSize((int)multipartFile.getSize());
            multipartFile.transferTo(new File(creditFile.getFilePath()));
            fileAndJobService.addFile(uploadFileDto, creditFile);
            verifyRunWay(uploadFileDto, creditFile.getFilePath(), creditFile.getUuid());
            returnResult = new ReturnResult<>();
            returnResult.setResult(creditFile.getUuid());
        }catch (BaseException e){
            returnResult = new ReturnResult<>(null, "fail", e.code, e.getMessage());
        } catch (Exception e) {
            returnResult = new ReturnResult<>(null,"fail","500",e.getMessage(),"系统异常");
        }
        return returnResult;
    }

    /**
     * 判断运行kettle的方式，定时，还是立即执行
     * @param uploadFileDto
     */
    public void verifyRunWay(UploadFileDto uploadFileDto, String filepath, String uuid){
        String command = buildCommand(uploadFileDto, filepath, uuid);
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
        kettleModel.setJob(creditDataType.getDataProcess());
        Map<String, String> param = kettleModel.getParam();
        param.put(environment.getProperty("kettle.repository.param.two"), creditDataType.getDefaultTransfor());
        param.put(environment.getProperty("kettle.repository.param.one"), filepath);
        param.put(environment.getProperty("kettle.repository.param.three"), environment.getProperty("kettle.repository.value.three")+uuid);
        param.put(environment.getProperty("kettle.repository.param.four"), uploadFileDto.getOrganizationCode());
        return BuildCommandUtil.buildKitchenLinux(kettlePath, kettleModel);
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
