package com.kettle.soso.mybatis.dal.bo.impl;

import com.kettle.soso.mybatis.dal.bo.CreditDataTypeBo;
import com.kettle.soso.mybatis.dal.mapper.CreditDataTypeMapperExt;
import com.kettle.soso.mybatis.dal.model.CreditDataType;
import com.kettle.soso.mybatis.dal.model.CreditDataTypeExample;
import org.apache.commons.collections4.CollectionUtils;
import org.beans.AbstractGogoBoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreditDataTypeBoImpl extends org.beans.AbstractGogoBoImpl<com.kettle.soso.mybatis.dal.model.CreditDataType, CreditDataTypeMapperExt, com.kettle.soso.mybatis.dal.model.CreditDataTypeExample> implements CreditDataTypeBo {

    @Autowired
    public void setBaseMapper(CreditDataTypeMapperExt mapper) {
        setMapper(mapper);
    }


    /**
     * 根据data_code查找对应的流程
     * @param dataCode
     * @return
     */
    @Override
    public Optional<String> selectDataProcess(String dataCode) {
        CreditDataTypeExample creditDataTypeExample = new CreditDataTypeExample();
        creditDataTypeExample.createCriteria().andDataCodeEqualTo(dataCode);
        List<CreditDataType> creditDataTypes = this.selectByExample(creditDataTypeExample);
        return CollectionUtils.isEmpty(creditDataTypes) ? Optional.empty() : Optional.ofNullable(creditDataTypes.get(0)).map(CreditDataType::getDataProcess);
    }
}