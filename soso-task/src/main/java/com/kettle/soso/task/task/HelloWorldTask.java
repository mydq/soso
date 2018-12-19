package com.kettle.soso.task.task;

import com.kettle.soso.task.base.BaseJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @Author: csz
 * @Date: 2018/12/18 17:34
 */
public class HelloWorldTask implements BaseJob {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        jobExecutionContext.getJobDetail();
        System.out.println("这是第一个任务");
    }
}
