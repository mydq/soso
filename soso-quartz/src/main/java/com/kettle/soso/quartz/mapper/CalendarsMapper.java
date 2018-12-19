package com.kettle.soso.quartz.mapper;

import com.kettle.soso.quartz.model.Calendars;
import com.kettle.soso.quartz.model.CalendarsExample;
import com.kettle.soso.quartz.model.CalendarsKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CalendarsMapper {
    /**
     *  根据指定的条件获取数据库记录数,qrtz_calendars
     *
     * @param example
     */
    long countByExample(CalendarsExample example);

    /**
     *  根据指定的条件删除数据库符合条件的记录,qrtz_calendars
     *
     * @param example
     */
    int deleteByExample(CalendarsExample example);

    /**
     *  根据主键删除数据库的记录,qrtz_calendars
     *
     * @param key
     */
    int deleteByPrimaryKey(CalendarsKey key);

    /**
     *  新写入数据库记录,qrtz_calendars
     *
     * @param record
     */
    int insert(Calendars record);

    /**
     *  动态字段,写入数据库记录,qrtz_calendars
     *
     * @param record
     */
    int insertSelective(Calendars record);

    /**
     * ,qrtz_calendars
     *
     * @param example
     */
    List<Calendars> selectByExampleWithBLOBs(CalendarsExample example);

    /**
     *  根据指定的条件查询符合条件的数据库记录,qrtz_calendars
     *
     * @param example
     */
    List<Calendars> selectByExample(CalendarsExample example);

    /**
     *  根据指定主键获取一条数据库记录,qrtz_calendars
     *
     * @param key
     */
    Calendars selectByPrimaryKey(CalendarsKey key);

    /**
     *  动态根据指定的条件来更新符合条件的数据库记录,qrtz_calendars
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") Calendars record, @Param("example") CalendarsExample example);

    /**
     * ,qrtz_calendars
     *
     * @param record
     * @param example
     */
    int updateByExampleWithBLOBs(@Param("record") Calendars record, @Param("example") CalendarsExample example);

    /**
     *  根据指定的条件来更新符合条件的数据库记录,qrtz_calendars
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") Calendars record, @Param("example") CalendarsExample example);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,qrtz_calendars
     *
     * @param record
     */
    int updateByPrimaryKeySelective(Calendars record);

    /**
     * ,qrtz_calendars
     *
     * @param record
     */
    int updateByPrimaryKeyWithBLOBs(Calendars record);

    int insertBatchSelective(List<Calendars> records);

    int updateBatchByPrimaryKeySelective(List<Calendars> records);
}