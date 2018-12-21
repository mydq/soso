package com.kettle.soso.task.interfaces;

import com.kettle.soso.common.exceptions.AddJobErrorException;
import com.kettle.soso.task.service.JobAndTriggerService;
import org.quartz.JobDataMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: csz
 * @Date: 2018/12/20 14:40
 */
@Component
public class SchedulerJobInterface {


    @Autowired
    private JobAndTriggerService jobAndTriggerService;


    public void addKettleSchedulerJob(String groupName, String command, String expression, boolean isShell){
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("command",command);
        jobDataMap.put("isShell",isShell);
        try {
            jobAndTriggerService.addJob("com.kettle.soso.task.task.KettleTask",groupName,expression, jobDataMap);
        } catch (Exception e) {
            throw new AddJobErrorException("添加job异常,此job命令为："+command);
        }
    }

}
