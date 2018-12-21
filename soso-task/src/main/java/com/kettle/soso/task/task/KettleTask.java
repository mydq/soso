package com.kettle.soso.task.task;

import com.kettle.soso.common.utils.CommandUtil;
import com.kettle.soso.task.base.BaseJob;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * kettle 任务
 * @Author: csz
 * @Date: 2018/12/20 14:19
 */
public class KettleTask implements BaseJob {



    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        CommandUtil.runLinuxPrint(String.valueOf(jobDataMap.get("command")),Boolean.valueOf(String.valueOf(jobDataMap.get("isShell"))));
    }


}
