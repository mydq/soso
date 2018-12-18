package com.kettle.soso.start;

import com.kettle.soso.mybatis.dal.DruidConfiguration;
import com.kettle.soso.mybatis.dal.MybatisConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({MybatisConfiguration.class, DruidConfiguration.class})
public class KettleApplication {

    public static void main(String[] args) {
        SpringApplication.run(KettleApplication.class, args);
    }
}
