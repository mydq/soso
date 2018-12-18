package com.kettle.soso.mybatis.dal.bo.impl;

import com.kettle.soso.mybatis.dal.bo.FormDemoBo;
import com.kettle.soso.mybatis.dal.mapper.FormDemoMapperExt;
import com.kettle.soso.mybatis.dal.model.FormDemo;
import com.kettle.soso.mybatis.dal.model.FormDemoExample;
import org.beans.AbstractGogoBoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormDemoBoImpl extends org.beans.AbstractGogoBoImpl<com.kettle.soso.mybatis.dal.model.FormDemo, FormDemoMapperExt, com.kettle.soso.mybatis.dal.model.FormDemoExample> implements FormDemoBo {

    @Autowired
    public void setBaseMapper(FormDemoMapperExt mapper) {
        setMapper(mapper);
    }
}