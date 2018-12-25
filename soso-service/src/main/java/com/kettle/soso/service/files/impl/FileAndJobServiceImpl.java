package com.kettle.soso.service.files.impl;

import com.kettle.soso.common.dto.UploadFileDto;
import com.kettle.soso.common.exceptions.OrgNoNotExistException;
import com.kettle.soso.mybatis.dal.bo.CreditFileBo;
import com.kettle.soso.mybatis.dal.bo.CreditOrganizationBo;
import com.kettle.soso.mybatis.dal.configs4xa.UserBean;
import com.kettle.soso.mybatis.dal.model.CreditFile;
import com.kettle.soso.mybatis.dal.model.CreditOrganization;
import com.kettle.soso.service.files.FileAndJobService;
import com.kettle.soso.hs_smc.mapper.OrgInfoMapper;
import com.kettle.soso.hs_smc.model.OrgInfo;
import com.kettle.soso.hs_smc.model.OrgInfoExample;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @Author: csz
 * @Date: 2018/12/19 12:40
 */
@Service
public class FileAndJobServiceImpl implements FileAndJobService {

    @Autowired
    @Qualifier("gogoPvgInfo")
    private UserBean userBean;
    @Autowired
    private CreditFileBo creditFileBo;
    @Autowired
    private CreditOrganizationBo creditOrganizationBo;
    @Autowired
    private OrgInfoMapper orgInfoMapper;

    /**
     * 文件添加
     * @param uploadFileDto
     * @param creditFile
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addFile(UploadFileDto uploadFileDto, CreditFile creditFile) {
        userBean.setUserId(uploadFileDto.getOrganizationCode());
        Optional<CreditOrganization> creditOrganization = creditOrganizationBo.selectByOrganizationCode(uploadFileDto.getOrganizationCode());
        if (creditOrganization.isPresent()){
            CreditOrganization creditOrganization1 = creditOrganization.get();
            creditOrganization1.setFileCount(creditOrganization1.getFileCount() + 1);
            creditOrganizationBo.update(creditOrganization1);
        }else {
            OrgInfoExample orgInfoExample = new OrgInfoExample();
            orgInfoExample.createCriteria().andOrgnoEqualTo(uploadFileDto.getOrganizationCode());
            List<OrgInfo> orgInfos = orgInfoMapper.selectByExample(orgInfoExample);
            if (CollectionUtils.isEmpty(orgInfos)){
                throw new OrgNoNotExistException(uploadFileDto.getOrganizationCode() + "-该组织不存在");
            }else {
                creditOrganizationBo.addAndCountByOrganizationCode(uploadFileDto.getOrganizationCode(), orgInfos.get(0).getOrgname());
            }
        }
        creditFile.setFileNewName(creditFile.getUuid());
        creditFile.setDataCode(uploadFileDto.getDataCode());
        creditFileBo.insert(creditFile);
    }
}
