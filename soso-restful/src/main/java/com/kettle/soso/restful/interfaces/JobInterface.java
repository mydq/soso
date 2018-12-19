package com.kettle.soso.restful.interfaces;

import com.kettle.soso.common.model.ReturnResult;
import com.kettle.soso.mybatis.dal.bo.CreditFileBo;
import com.kettle.soso.mybatis.dal.mapper.CreditFileMapperExt;
import com.kettle.soso.mybatis.dal.model.CreditFile;
import com.kettle.soso.quartz.mapper.CalendarsMapper;
import com.kettle.soso.quartz.mapper.JobDetailsMapper;
import com.kettle.soso.quartz.model.Calendars;
import com.kettle.soso.quartz.model.JobDetails;
import com.kettle.soso.restful.entity.UploadFileVo;
import com.kettle.soso.service.files.FileAndJobService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * 文件上传
     * @param uploadFileVo
     * @param multipartFile
     * @return
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public ReturnResult uploadFile(UploadFileVo uploadFileVo, @RequestParam("file") MultipartFile multipartFile){
        //获取文件名
        String originalFilename = multipartFile.getOriginalFilename();
        String baseName = FilenameUtils.getBaseName(originalFilename);
        String extension = FilenameUtils.getExtension(originalFilename);
        UUID uuid = UUID.randomUUID();
        CreditFile creditFile = new CreditFile();

        fileAndJobService.addFile();
        return new ReturnResult();
    }

}
