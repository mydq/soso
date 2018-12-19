package com.kettle.soso.restful.interfaces;

import com.kettle.soso.common.exceptions.BaseException;
import com.kettle.soso.common.exceptions.FileErrorException;
import com.kettle.soso.common.model.ReturnResult;
import com.kettle.soso.mybatis.dal.model.CreditFile;
import com.kettle.soso.common.vo.UploadFileVo;
import com.kettle.soso.service.files.FileAndJobService;
import com.kettle.soso.service.organization.OrganizationService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
    FileAndJobService fileAndJobService;
    @Autowired
    OrganizationService organizationService;

    /**
     * 文件上传
     * @param uploadFileVo
     * @param multipartFile
     * @return
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public ReturnResult uploadFile(UploadFileVo uploadFileVo, @RequestParam("file") MultipartFile multipartFile){
        ReturnResult<Object> returnResult = null;
        try {
            String documentPath = organizationService.selectOrganizationDocument(uploadFileVo.getOrganizationCode()).orElseThrow(FileErrorException::new);
            String originalFilename = multipartFile.getOriginalFilename();
            //获取文件名
            String extension = FilenameUtils.getExtension(originalFilename);
            String uuid = UUID.randomUUID().toString();
            CreditFile creditFile = new CreditFile();
            creditFile.setUuid(uuid);
            creditFile.setFileNewName(uuid);
            creditFile.setFileOldName(FilenameUtils.getBaseName(originalFilename));
            String filepath = StringUtils.join(documentPath, "/", uuid, ".", extension);
            creditFile.setFilePath(filepath);
            creditFile.setFileSuffix(extension);
            creditFile.setFileType(multipartFile.getContentType());
            creditFile.setSize((int)multipartFile.getSize());
            creditFile.setVerifyMd5(DigestUtils.md5DigestAsHex(multipartFile.getInputStream()));
            multipartFile.transferTo(new File(filepath));
            fileAndJobService.addFile(uploadFileVo.getOrganizationCode(), creditFile);
            returnResult = new ReturnResult<>();
        }catch (BaseException e){
            returnResult = new ReturnResult<>(null, "fail", e.code, e.getMessage());
        } catch (Exception e) {
            returnResult = new ReturnResult<>(null,"fail","500",e.getMessage(),"系统异常");
        }
        return returnResult;
    }

    /**
     * 判断运行kettle的方式，定时，还是立即执行
     * @param uploadFileVo
     */
    public void verifyRunWay(UploadFileVo uploadFileVo, String filepath){
        if (StringUtils.isBlank(uploadFileVo.getExpression())){

        }else {

        }
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ReturnResult test(UploadFileVo uploadFileVo){
        try {
            organizationService.selectOrganizationDocument("11");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ReturnResult();
    }

}
