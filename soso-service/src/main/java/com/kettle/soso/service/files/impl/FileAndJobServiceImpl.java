package com.kettle.soso.service.files.impl;

import com.kettle.soso.common.dto.UploadFileDto;
import com.kettle.soso.mybatis.dal.bo.CreditFileBo;
import com.kettle.soso.mybatis.dal.bo.CreditOrganizationBo;
import com.kettle.soso.mybatis.dal.configs4xa.UserBean;
import com.kettle.soso.mybatis.dal.model.CreditFile;
import com.kettle.soso.quartz.mapper.CalendarsMapper;
import com.kettle.soso.service.files.FileAndJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: csz
 * @Date: 2018/12/19 12:40
 */
@Service
public class FileAndJobServiceImpl implements FileAndJobService {

    @Autowired
    @Qualifier("gogoPvgInfo")
    private UserBean userBean;
    @Autowired
    private CreditFileBo creditFileBo;
    @Autowired
    private CreditOrganizationBo creditOrganizationBo;

    /**
     * 文件添加
     * @param uploadFileDto
     * @param creditFile
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addFile(UploadFileDto uploadFileDto, CreditFile creditFile) {
        userBean.setUserId(uploadFileDto.getOrganizationCode());
        creditOrganizationBo.updateOrAddCountByOrganizationCode(uploadFileDto.getOrganizationCode(), uploadFileDto.getOrganizationName());

        creditFile.setFileNewName(creditFile.getUuid());
        creditFile.setDataCode(uploadFileDto.getDataCode());
        creditFileBo.insert(creditFile);
    }
}
