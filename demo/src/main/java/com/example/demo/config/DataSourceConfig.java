package com.example.demo.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

@Configuration
//@PropertySource("classpath:datasource.properties")
public class DataSourceConfig {
    
    private Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);
    
    @Bean(name = "primaryDataSource")
    @Primary
    @Qualifier("primaryDataSource")
    @ConfigurationProperties(prefix="spring.datasource" )
    public DataSource primaryDataSource() {
        logger.info("第一个数据库连接池创建中.......");
        return DataSourceBuilder.create().build();
    }

   
    @Bean(name = "secondaryDataSource")
    @Qualifier("secondaryDataSource")
    @ConfigurationProperties(prefix="spring.seconddatasource.second")
    public DataSource secondaryDataSource() {
        logger.info("第二个数据库连接池创建中......");
        return DataSourceBuilder.create().build();
    }
}
