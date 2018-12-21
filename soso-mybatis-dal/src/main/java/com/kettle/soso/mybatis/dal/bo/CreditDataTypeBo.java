package com.kettle.soso.mybatis.dal.bo;

import com.kettle.soso.mybatis.dal.model.CreditDataType;
import com.kettle.soso.mybatis.dal.model.CreditDataTypeExample;
import org.beans.GogoBo;

import java.util.Optional;

public interface CreditDataTypeBo extends org.beans.GogoBo<com.kettle.soso.mybatis.dal.model.CreditDataType, com.kettle.soso.mybatis.dal.model.CreditDataTypeExample> {

    /**
     * 根据data_code查找对应的流程
     * @param dataCode
     * @return
     */
    Optional<String> selectDataProcess(String dataCode);
}