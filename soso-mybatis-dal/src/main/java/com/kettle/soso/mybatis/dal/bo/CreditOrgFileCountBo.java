package com.kettle.soso.mybatis.dal.bo;

public interface CreditOrgFileCountBo extends org.beans.GogoBo<com.kettle.soso.mybatis.dal.model.CreditOrgFileCount, com.kettle.soso.mybatis.dal.model.CreditOrgFileCountExample> {

    /**
     * 修改或者添加
     * @param organizationCode
     */
    void updateOrAdd(String organizationCode);

}