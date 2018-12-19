package com.kettle.soso.quartz.mapper;

import com.kettle.soso.quartz.model.JobDetails;
import com.kettle.soso.quartz.model.JobDetailsExample;
import com.kettle.soso.quartz.model.JobDetailsKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JobDetailsMapper {
    /**
     *  根据指定的条件获取数据库记录数,qrtz_job_details
     *
     * @param example
     */
    long countByExample(JobDetailsExample example);

    /**
     *  根据指定的条件删除数据库符合条件的记录,qrtz_job_details
     *
     * @param example
     */
    int deleteByExample(JobDetailsExample example);

    /**
     *  根据主键删除数据库的记录,qrtz_job_details
     *
     * @param key
     */
    int deleteByPrimaryKey(JobDetailsKey key);

    /**
     *  新写入数据库记录,qrtz_job_details
     *
     * @param record
     */
    int insert(JobDetails record);

    /**
     *  动态字段,写入数据库记录,qrtz_job_details
     *
     * @param record
     */
    int insertSelective(JobDetails record);

    /**
     * ,qrtz_job_details
     *
     * @param example
     */
    List<JobDetails> selectByExampleWithBLOBs(JobDetailsExample example);

    /**
     *  根据指定的条件查询符合条件的数据库记录,qrtz_job_details
     *
     * @param example
     */
    List<JobDetails> selectByExample(JobDetailsExample example);

    /**
     *  根据指定主键获取一条数据库记录,qrtz_job_details
     *
     * @param key
     */
    JobDetails selectByPrimaryKey(JobDetailsKey key);

    /**
     *  动态根据指定的条件来更新符合条件的数据库记录,qrtz_job_details
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") JobDetails record, @Param("example") JobDetailsExample example);

    /**
     * ,qrtz_job_details
     *
     * @param record
     * @param example
     */
    int updateByExampleWithBLOBs(@Param("record") JobDetails record, @Param("example") JobDetailsExample example);

    /**
     *  根据指定的条件来更新符合条件的数据库记录,qrtz_job_details
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") JobDetails record, @Param("example") JobDetailsExample example);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,qrtz_job_details
     *
     * @param record
     */
    int updateByPrimaryKeySelective(JobDetails record);

    /**
     * ,qrtz_job_details
     *
     * @param record
     */
    int updateByPrimaryKeyWithBLOBs(JobDetails record);

    /**
     *  根据主键来更新符合条件的数据库记录,qrtz_job_details
     *
     * @param record
     */
    int updateByPrimaryKey(JobDetails record);

    int insertBatchSelective(List<JobDetails> records);

    int updateBatchByPrimaryKeySelective(List<JobDetails> records);
}