package com.kettle.soso.service.files.impl;

import com.kettle.soso.mybatis.dal.bo.CreditFileBo;
import com.kettle.soso.mybatis.dal.bo.CreditOrgFileCountBo;
import com.kettle.soso.mybatis.dal.configs4xa.UserBean;
import com.kettle.soso.mybatis.dal.model.CreditFile;
import com.kettle.soso.mybatis.dal.model.CreditOrgFileCount;
import com.kettle.soso.quartz.mapper.CalendarsMapper;
import com.kettle.soso.service.files.FileAndJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
    private CreditOrgFileCountBo creditOrgFileCountBo;
    @Autowired
    private CreditFileBo creditFileBo;

    @Autowired
    private CalendarsMapper calendarsMapper;

    /**
     * 文件添加
     * @param userId
     * @param creditFile
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addFile(String userId, CreditFile creditFile) {
        userBean.setUserId(userId);
        creditFileBo.insert(creditFile);
        creditOrgFileCountBo.updateOrAdd(userId);

    }
}
