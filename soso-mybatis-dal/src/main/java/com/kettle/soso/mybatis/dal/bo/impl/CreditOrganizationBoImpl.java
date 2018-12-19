package com.kettle.soso.mybatis.dal.bo.impl;

import com.kettle.soso.mybatis.dal.bo.CreditOrganizationBo;
import com.kettle.soso.mybatis.dal.mapper.CreditOrganizationMapperExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditOrganizationBoImpl extends org.beans.AbstractGogoBoImpl<com.kettle.soso.mybatis.dal.model.CreditOrganization, CreditOrganizationMapperExt, com.kettle.soso.mybatis.dal.model.CreditOrganizationExample> implements CreditOrganizationBo {

    @Autowired
    public void setBaseMapper(CreditOrganizationMapperExt mapper) {
        setMapper(mapper);
    }
}