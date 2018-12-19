package com.kettle.soso.service.organization.impl;

import com.kettle.soso.common.utils.FileBean;
import com.kettle.soso.mybatis.dal.bo.CreditOrganizationBo;
import com.kettle.soso.service.organization.OrganizationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Author: csz
 * @Date: 2018/12/19 13:55
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    CreditOrganizationBo creditOrganizationBo;

    @Value("${document.path}")
    String documentPath;

    /**
     * 验证该组织是否有文档
     * @param organizationCode
     * @return
     */
    @Override
    public Optional<String> selectOrganizationDocument(String organizationCode) throws Exception{
        System.out.println(documentPath);
        if (creditOrganizationBo.selectByOrganizationCode(organizationCode).isPresent()){
            String path = FileBean.notExistsToCreateDir(StringUtils.join(documentPath, "/", organizationCode));
            return Optional.ofNullable(path);
        }
        return Optional.empty();
    }
}
