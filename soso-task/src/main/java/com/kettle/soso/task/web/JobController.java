package com.kettle.soso.task.web;

import com.kettle.soso.task.service.JobAndTriggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: csz
 * @Date: 2018/12/18 17:50
 */
@RestController
@RequestMapping("job")
public class JobController {

    @Autowired
    private JobAndTriggerService jobAndTriggerService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String addJob(String jobName, String groupName, String cron){
        String message = null;
        try {
            jobAndTriggerService.addJob("com.kettle.soso.task.task.HelloWorldTask", "1", "* * * * * ?",null);
            message = "success";
        }catch (Exception e){
            e.printStackTrace();
            message = "fail";
        }
        return message;
    }
}
