package com.kettle.soso.hs_smc.mapper;

import com.kettle.soso.hs_smc.model.OrgInfo;
import com.kettle.soso.hs_smc.model.OrgInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrgInfoMapper {
    /**
     *  根据指定的条件获取数据库记录数,org_info
     *
     * @param example
     */
    long countByExample(OrgInfoExample example);

    /**
     *  根据指定的条件删除数据库符合条件的记录,org_info
     *
     * @param example
     */
    int deleteByExample(OrgInfoExample example);

    /**
     *  根据主键删除数据库的记录,org_info
     *
     * @param orgid
     */
    int deleteByPrimaryKey(Long orgid);

    /**
     *  新写入数据库记录,org_info
     *
     * @param record
     */
    int insert(OrgInfo record);

    /**
     *  动态字段,写入数据库记录,org_info
     *
     * @param record
     */
    int insertSelective(OrgInfo record);

    /**
     *  根据指定的条件查询符合条件的数据库记录,org_info
     *
     * @param example
     */
    List<OrgInfo> selectByExample(OrgInfoExample example);

    /**
     *  根据指定主键获取一条数据库记录,org_info
     *
     * @param orgid
     */
    OrgInfo selectByPrimaryKey(Long orgid);

    /**
     *  动态根据指定的条件来更新符合条件的数据库记录,org_info
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") OrgInfo record, @Param("example") OrgInfoExample example);

    /**
     *  根据指定的条件来更新符合条件的数据库记录,org_info
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") OrgInfo record, @Param("example") OrgInfoExample example);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,org_info
     *
     * @param record
     */
    int updateByPrimaryKeySelective(OrgInfo record);

    /**
     *  根据主键来更新符合条件的数据库记录,org_info
     *
     * @param record
     */
    int updateByPrimaryKey(OrgInfo record);

    int insertBatchSelective(List<OrgInfo> records);

    int updateBatchByPrimaryKeySelective(List<OrgInfo> records);
}