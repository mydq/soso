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
}