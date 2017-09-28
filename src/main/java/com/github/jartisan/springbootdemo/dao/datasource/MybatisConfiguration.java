package com.github.jartisan.springbootdemo.dao.datasource;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.apache.bcel.util.ClassLoaderRepository;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.github.jartisan.springbootdemo.configuration.DataSourceConfiguration;

@Configuration
@AutoConfigureAfter({DataSourceConfiguration.class})
public class MybatisConfiguration extends MybatisAutoConfiguration {

    public MybatisConfiguration(MybatisProperties properties, ObjectProvider<Interceptor[]> interceptorsProvider,
			ResourceLoader resourceLoader, ObjectProvider<DatabaseIdProvider> databaseIdProvider,
			ObjectProvider<List<ConfigurationCustomizer>> configurationCustomizersProvider) {
		super(properties, interceptorsProvider, resourceLoader, databaseIdProvider, configurationCustomizersProvider);
	}


	private static Log logger = LogFactory.getLog(MybatisConfiguration.class);

    @Resource(name = "masterDataSource")
    private DataSource masterDataSource;
    @Resource(name = "slaveDataSource")
    private DataSource slaveDataSource;

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        logger.info("-------------------- 重载父类 sqlSessionFactory init ---------------------");
        return super.sqlSessionFactory(roundRobinDataSouceProxy());
    }


    public AbstractRoutingDataSource roundRobinDataSouceProxy(){
        DbRouteDataSource proxy = new DbRouteDataSource();
        @SuppressWarnings("unchecked")
		Map<Object,Object> targetDataResources = new ClassLoaderRepository.SoftHashMap();
        targetDataResources.put(DbContextHolder.DbType.MASTER,masterDataSource);
        targetDataResources.put(DbContextHolder.DbType.SLAVE,slaveDataSource);
        proxy.setDefaultTargetDataSource(masterDataSource);
        proxy.setTargetDataSources(targetDataResources);
        return proxy;
    }
}
