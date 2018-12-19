package com.kettle.soso.mybatis.dal.configs4xa;//package com.kettle.soso.mybatis.dal.configs.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.cj.jdbc.MysqlXADataSource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 数据源配置
 * @Author: csz
 * @Date: 2018/12/18 12:55
 */
public abstract class AbstractDataSourceConfig {

    /**
     * 创建数据源
     * @param environment
     * @param prefix
     * @param dataSourceName
     * @return
     */
    protected DataSource getDataSource(Environment environment, String prefix, String dataSourceName){
        Properties build = build(environment, prefix);
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(build.getProperty("url"));
        mysqlXaDataSource.setPassword(build.getProperty("password"));
        mysqlXaDataSource.setUser(build.getProperty("username"));


        //创建Atomikos全局事务，所有的事务注册进来
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName(dataSourceName); //dataSourceName这个数据源的注册
        xaDataSource.setXaProperties(build);
        return xaDataSource;
    }


    /**
     * 主要针对druid数据库链接池
     * @param env
     * @param prefix
     * @return
     */
    protected Properties build(Environment env, String prefix) {
        Properties prop = new Properties();
        prop.put("url", env.getProperty(prefix + "url"));
        prop.put("username", env.getProperty(prefix + "username"));
        prop.put("password", env.getProperty(prefix + "password"));
        prop.put("driverClassName", env.getProperty(prefix + "driverClassName", ""));
        prop.put("initialSize", env.getProperty(prefix + "initialSize", Integer.class));
        prop.put("maxActive", env.getProperty(prefix + "maxActive", Integer.class));
        prop.put("minIdle", env.getProperty(prefix + "minIdle", Integer.class));
        prop.put("maxWait", env.getProperty(prefix + "maxWait", Integer.class));
        prop.put("poolPreparedStatements", env.getProperty(prefix + "poolPreparedStatements", Boolean.class));
        prop.put("maxPoolPreparedStatementPerConnectionSize",
                env.getProperty(prefix + "maxPoolPreparedStatementPerConnectionSize", Integer.class));
        prop.put("validationQuery", env.getProperty(prefix + "validationQuery"));
        prop.put("validationQueryTimeout", env.getProperty(prefix + "validationQueryTimeout", Integer.class));
        prop.put("testOnBorrow", env.getProperty(prefix + "testOnBorrow", Boolean.class));
        prop.put("testOnReturn", env.getProperty(prefix + "testOnReturn", Boolean.class));
        prop.put("testWhileIdle", env.getProperty(prefix + "testWhileIdle", Boolean.class));
        prop.put("timeBetweenEvictionRunsMillis", env.getProperty(prefix + "timeBetweenEvictionRunsMillis", Integer.class));
        prop.put("minEvictableIdleTimeMillis", env.getProperty(prefix + "minEvictableIdleTimeMillis", Integer.class));
        prop.put("filters", env.getProperty(prefix + "filters"));
        prop.put("connectionProperties", env.getProperty(prefix + "connectionProperties"));
        prop.put("useGlobalDataSourceStat", env.getProperty(prefix + "useGlobalDataSourceStat",Boolean.class));
        return prop;
    }


}
