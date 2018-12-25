package com.kettle.soso.start;

import com.kettle.soso.mybatis.dal.DruidConfiguration;
import com.kettle.soso.mybatis.dal.MybatisConfigOne;
import com.kettle.soso.mybatis.dal.MybatisConfigThree;
import com.kettle.soso.mybatis.dal.MybatisConfigTwo;
import com.kettle.soso.restful.WebConfiguration;
import com.kettle.soso.service.ServiceConfiguration;
import com.kettle.soso.task.SchedulerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({MybatisConfigOne.class,
        MybatisConfigTwo.class,
        MybatisConfigThree.class,
        DruidConfiguration.class,
        SchedulerConfig.class,
        WebConfiguration.class,
        ServiceConfiguration.class})
public class KettleApplication extends SpringBootServletInitializer{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(KettleApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(KettleApplication.class, args);
    }
}
