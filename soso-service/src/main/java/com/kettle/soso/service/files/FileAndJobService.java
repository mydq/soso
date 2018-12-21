package com.kettle.soso.service.files;

import com.kettle.soso.common.dto.UploadFileDto;
import com.kettle.soso.mybatis.dal.model.CreditFile;

/**
 * @Author: csz
 * @Date: 2018/12/19 12:37
 */
public interface FileAndJobService {

    /**
     * 文件添加
     * @param uploadFileDto
     * @param creditFile
     */
    void addFile(UploadFileDto uploadFileDto, CreditFile creditFile);

}
