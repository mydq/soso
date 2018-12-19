package com.kettle.soso.mybatis.dal.bo.impl;

import com.kettle.soso.mybatis.dal.bo.CreditOrgFileCountBo;
import com.kettle.soso.mybatis.dal.mapper.CreditOrgFileCountMapperExt;
import com.kettle.soso.mybatis.dal.model.CreditOrgFileCount;
import com.kettle.soso.mybatis.dal.model.CreditOrgFileCountExample;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CreditOrgFileCountBoImpl extends org.beans.AbstractGogoBoImpl<com.kettle.soso.mybatis.dal.model.CreditOrgFileCount, CreditOrgFileCountMapperExt, com.kettle.soso.mybatis.dal.model.CreditOrgFileCountExample> implements CreditOrgFileCountBo {

    @Autowired
    public void setBaseMapper(CreditOrgFileCountMapperExt mapper) {
        setMapper(mapper);
    }


    /**
     * 修改或者添加
     * @param organizationCode
     */
    @Override
    public void updateOrAdd(String organizationCode) {
        CreditOrgFileCountExample creditOrgFileCountExample = new CreditOrgFileCountExample();
        creditOrgFileCountExample.createCriteria().andOrganizationCodeEqualTo(organizationCode);
        List<CreditOrgFileCount> creditOrgFileCounts = this.mapper.selectByExample(creditOrgFileCountExample);
        if (CollectionUtils.isEmpty(creditOrgFileCounts)){
            CreditOrgFileCount creditOrgFileCount = new CreditOrgFileCount();
            creditOrgFileCount.setCount(1);
            creditOrgFileCount.setOrganizationCode(organizationCode);
            creditOrgFileCount.setUuid(UUID.randomUUID().toString());
            this.mapper.insertSelective(creditOrgFileCount);
        }else {
            CreditOrgFileCount creditOrgFileCount = creditOrgFileCounts.get(0);
            creditOrgFileCount.setCount(creditOrgFileCount.getCount() + 1);
            this.mapper.updateByPrimaryKeySelective(creditOrgFileCount);
        }
    }
}