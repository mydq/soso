package com.kettle.soso.mybatis.dal;

import com.kettle.soso.mybatis.dal.configs4xa.AbstractDataSourceConfig;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * // basePackages 最好分开配置 如果放在同一个文件夹可能会报错
 * //跟之前都一样。扫包然后指定工厂
 * //没有配置事务管理器，相当于将本地事务注册到Atomikos全局事务
 * @Author: csz
 * @Date: 2018/12/18 16:06
 */
@Configuration
@MapperScan(basePackages = "com.kettle.soso.quartz.mapper", sqlSessionTemplateRef = "dataSourceTwoSqlSessionTemplate")
public class MybatisConfigTwo extends AbstractDataSourceConfig {
    //对接数据库的实体层
    static final String ALIASES_PACKAGE = "com.kettle.soso.quartz.model";

    static final String MAPPER_LOCATION = "classpath:com/kettle/soso/quartz/mapper/*.xml";

    /**
     * 配置数据源
     * @param environment
     * @return
     * @throws SQLException
     */
    @Bean(name = "dataSourceTwo")
    public DataSource dataSourceTwo(Environment environment) throws SQLException {
        return getDataSource(environment, "spring.datasource.druid.two.", "dataSourceTwo");
    }


    @Bean(name = "dataSourceTwoSqlSessionFactory")
    public SqlSessionFactory dataSourceTwoSqlSessionFactory(@Qualifier("dataSourceTwo") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage(ALIASES_PACKAGE);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
        return bean.getObject();
    }


    @Bean(name = "dataSourceTwoSqlSessionTemplate")
    public SqlSessionTemplate dataSourceTwoSqlSessionTemplate(
            @Qualifier("dataSourceTwoSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}