package com.kettle.soso.task.service;

import com.kettle.soso.task.base.BaseJob;
import org.apache.commons.collections4.CollectionUtils;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @Author: csz
 * @Date: 2018/12/18 16:48
 */
@Service
public class JobAndTriggerSerivceImpl implements JobAndTriggerService {

    @Autowired
    private Scheduler scheduler;


//    @Autowired
    // 加入Qulifier注解，通过名称注入bean
    //	@Qualifier("Scheduler")
//    private JobAndTriggerMapper jobAndTriggerMapper;


//    public Map<String, Object> getPageJob(PageUtil search) {
//        Pagination page = new Page<Object>(search.getPage(), search.getRows(), search.getSort());
//        List<JobAndTriggerDto> records = jobAndTriggerMapper.getJobAndTriggerDetails(page);
//        return search.getResultMap(page.getTotal(), records);
//    }

//    @Override
//    public JobAndTriggerDto getPageJobmod() {
//        return jobAndTriggerMapper.getJobAndTriggerDto();
//    }

    @Override
    public void addJob(String jobClassName, String jobGroupName, String cronExpression, JobDataMap jobDataMap) throws Exception {
        // 启动调度器
        scheduler.start();
        JobDetail jobDetail;
        if (Objects.isNull(jobDataMap)){
            jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass())
                    .withIdentity(jobClassName, jobGroupName).build();
        }else {
            // 构建job信息
            jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass()).usingJobData(jobDataMap)
                    .withIdentity(jobClassName, jobGroupName).build();

        }
        // 表达式调度构建器(即任务执行的时间)
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

        // 按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName, jobGroupName)
                .withSchedule(scheduleBuilder).build();

        try {
            scheduler.scheduleJob(jobDetail, trigger);
            System.out.println("创建定时任务成功");

        } catch (SchedulerException e) {
            System.out.println("创建定时任务失败" + e);
            throw new Exception("创建定时任务失败");
        }

    }

    @Override
    public void updateJob(String jobClassName, String jobGroupName, String cronExpression) throws Exception {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            System.out.println("更新定时任务失败" + e);
            throw new Exception("更新定时任务失败");
        }
    }

    @Override
    public void deleteJob(String jobClassName, String jobGroupName) throws Exception {
        scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName, jobGroupName));
        scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName, jobGroupName));
        scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));
    }

    @Override
    public void pauseJob(String jobClassName, String jobGroupName) throws Exception {
        scheduler.pauseJob(JobKey.jobKey(jobClassName, jobGroupName));
    }

    @Override
    public void resumejob(String jobClassName, String jobGroupName) throws Exception {
        scheduler.resumeJob(JobKey.jobKey(jobClassName, jobGroupName));
    }

    public static BaseJob getClass(String classname) throws Exception {
        Class<?> class1 = Class.forName(classname);
        return (BaseJob) class1.newInstance();
    }

}
