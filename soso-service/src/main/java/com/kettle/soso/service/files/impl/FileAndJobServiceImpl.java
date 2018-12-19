package com.kettle.soso.service.files.impl;

import com.kettle.soso.mybatis.dal.bo.CreditFileBo;
import com.kettle.soso.mybatis.dal.model.CreditFile;
import com.kettle.soso.quartz.mapper.CalendarsMapper;
import com.kettle.soso.quartz.model.Calendars;
import com.kettle.soso.service.files.FileAndJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: csz
 * @Date: 2018/12/19 12:40
 */
@Service
public class FileAndJobServiceImpl implements FileAndJobService {
    @Autowired
    private CreditFileBo creditFileBo;

    @Autowired
    private CalendarsMapper calendarsMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addFile() {
        CreditFile creditFile = new CreditFile();
        creditFile.setFileNewName("2222");

        Calendars calendars = new Calendars();
        byte[] bytes = {1, 3, 4};
        calendars.setCalendar(bytes);
        calendars.setCalendarName("222");
        calendars.setSchedName("2222");

        creditFileBo.insert(creditFile);
        calendarsMapper.insertSelective(calendars);
    }
}
