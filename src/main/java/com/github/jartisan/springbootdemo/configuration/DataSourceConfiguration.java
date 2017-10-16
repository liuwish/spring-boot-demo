package com.github.jartisan.springbootdemo.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/**
 * @ClassName: DataSourceConfiguration
 * @Description:数据源配置
 * @author: wjl
 * @date: 2016年2月26日 上午11:39:24
 */
@Configuration
@EnableTransactionManagement
public class DataSourceConfiguration {
	@Value("${druid.type}")
    private Class<? extends DataSource> dataSourceType;


    @Bean(name = "masterDataSource")
    @Primary
    @ConfigurationProperties(prefix = "druid.master")
    public DataSource masterDataSource(){
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = "slaveDataSource")
    @ConfigurationProperties(prefix = "druid.slave")
    public DataSource slaveDataSource(){
        return DataSourceBuilder.create().type(dataSourceType).build();
    }
}
