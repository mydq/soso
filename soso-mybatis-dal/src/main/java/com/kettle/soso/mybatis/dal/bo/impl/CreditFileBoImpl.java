package com.kettle.soso.mybatis.dal.bo.impl;

import com.kettle.soso.mybatis.dal.bo.CreditFileBo;
import com.kettle.soso.mybatis.dal.mapper.CreditFileMapperExt;
import com.kettle.soso.mybatis.dal.model.CreditFile;
import com.kettle.soso.mybatis.dal.model.CreditFileExample;
import org.beans.AbstractGogoBoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditFileBoImpl extends org.beans.AbstractGogoBoImpl<com.kettle.soso.mybatis.dal.model.CreditFile, CreditFileMapperExt, com.kettle.soso.mybatis.dal.model.CreditFileExample> implements CreditFileBo {

    @Autowired
    public void setBaseMapper(CreditFileMapperExt mapper) {
        setMapper(mapper);
    }
}