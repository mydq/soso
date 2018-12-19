package com.kettle.soso.service.organization;

import java.util.Optional;

/**
 * @Author: csz
 * @Date: 2018/12/19 13:53
 */
public interface OrganizationService {

    /**
     * 验证该组织是否有文档
     * @param organizationCode
     * @return
     */
    Optional<String> selectOrganizationDocument(String organizationCode) throws Exception;
}
