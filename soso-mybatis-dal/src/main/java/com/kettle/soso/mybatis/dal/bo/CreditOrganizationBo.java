package com.kettle.soso.mybatis.dal.bo;

import com.kettle.soso.mybatis.dal.model.CreditOrganization;
import com.kettle.soso.mybatis.dal.model.CreditOrganizationExample;
import org.beans.GogoBo;

import java.util.Optional;

public interface CreditOrganizationBo extends org.beans.GogoBo<com.kettle.soso.mybatis.dal.model.CreditOrganization, com.kettle.soso.mybatis.dal.model.CreditOrganizationExample> {

    /**
     * 根据组织code查询
     * @param organizationCode
     * @return
     */
    Optional<CreditOrganization> selectByOrganizationCode(String organizationCode);

}