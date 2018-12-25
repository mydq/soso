package com.kettle.soso.mybatis.dal.bo.impl;

import com.kettle.soso.mybatis.dal.bo.CreditOrganizationBo;
import com.kettle.soso.mybatis.dal.mapper.CreditOrganizationMapperExt;
import com.kettle.soso.mybatis.dal.model.CreditOrganization;
import com.kettle.soso.mybatis.dal.model.CreditOrganizationExample;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CreditOrganizationBoImpl extends org.beans.AbstractGogoBoImpl<com.kettle.soso.mybatis.dal.model.CreditOrganization, CreditOrganizationMapperExt, com.kettle.soso.mybatis.dal.model.CreditOrganizationExample> implements CreditOrganizationBo {

    @Autowired
    public void setBaseMapper(CreditOrganizationMapperExt mapper) {
        setMapper(mapper);
    }

    /**
     * 根据组织code查询
     * @param organizationCode
     * @return
     */
    @Override
    public Optional<CreditOrganization> selectByOrganizationCode(String organizationCode) {
        CreditOrganizationExample creditOrganizationExample = new CreditOrganizationExample();
        creditOrganizationExample.createCriteria().andOrganizationCodeEqualTo(organizationCode);
        List<CreditOrganization> creditOrganizations = this.mapper.selectByExample(creditOrganizationExample);
        return CollectionUtils.isNotEmpty(creditOrganizations) ? Optional.ofNullable(creditOrganizations.get(0)) : Optional.empty();
    }

    /**
     * 根据组织code查询，不存在则添加新的,存在则修改数量
     * @param organizationCode
     * @param organizationName
     * @return
     */
    @Override
    public void updateOrAddCountByOrganizationCode(String organizationCode, String organizationName) {
        CreditOrganizationExample creditOrganizationExample = new CreditOrganizationExample();
        creditOrganizationExample.createCriteria().andOrganizationCodeEqualTo(organizationCode);
        List<CreditOrganization> creditOrganizations = this.mapper.selectByExample(creditOrganizationExample);
        if (CollectionUtils.isEmpty(creditOrganizations)){
            this.mapper.insertSelective(initThis(organizationCode, organizationName));
        }else {
            CreditOrganization creditOrganization = creditOrganizations.get(0);
            creditOrganization.setFileCount(creditOrganization.getFileCount() + 1);
            this.mapper.updateByPrimaryKeySelective(creditOrganization);
        }
    }

    /**
     * 添加该组织
     * @param organizationCode
     * @param organizationName
     */
    @Override
    public void addAndCountByOrganizationCode(String organizationCode, String organizationName) {
        this.mapper.insertSelective(initThis(organizationCode, organizationName));
    }

    /**
     * 初始化该对象
     * @param organizationCode
     * @param organizationName
     * @return
     */
    private CreditOrganization initThis(String organizationCode, String organizationName){
        CreditOrganization creditOrganization = new CreditOrganization();
        creditOrganization.setUuid(UUID.randomUUID().toString());
        creditOrganization.setFileCount(1);
        creditOrganization.setOrganizationCode(organizationCode);
        creditOrganization.setOrganizationName(organizationName);
        return creditOrganization;
    }


}