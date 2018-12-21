package com.kettle.soso.service.organization.impl;

import com.kettle.soso.mybatis.dal.bo.CreditOrganizationBo;
import com.kettle.soso.service.organization.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: csz
 * @Date: 2018/12/19 13:55
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    CreditOrganizationBo creditOrganizationBo;


}
