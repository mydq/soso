package com.kettle.soso.quartz.model;

import java.io.Serializable;

public class Calendars extends CalendarsKey implements Serializable {
    /**
     * 
     * 表字段 : qrtz_calendars.CALENDAR
     */
    private byte[] calendar;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table qrtz_calendars
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * 获取  字段:qrtz_calendars.CALENDAR
     *
     * @return qrtz_calendars.CALENDAR, 
     */
    public byte[] getCalendar() {
        return calendar;
    }

    /**
     * 设置  字段:qrtz_calendars.CALENDAR
     *
     * @param calendar the value for qrtz_calendars.CALENDAR, 
     */
    public void setCalendar(byte[] calendar) {
        this.calendar = calendar;
    }
}