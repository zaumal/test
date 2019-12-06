package com.zaumal.test.mvc.initializer.conf;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Controller;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.zaumal.test.mvc.initializer.repository")
@ComponentScan(basePackages = "com.zaumal.test.mvc.initializer",excludeFilters = @Filter(Controller.class))
public class RootConf {
	@Bean
	public DruidDataSource druidDataSource() throws SQLException {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl("jdbc:mysql://192.168.7.8:3306/test?useSSL=false&characterEncoding=utf-8");
		dataSource.setUsername("root");
		dataSource.setPassword("bjBI123*");
		dataSource.setInitialSize(5);
		dataSource.setMinIdle(5);
		dataSource.setMaxActive(10);
		dataSource.setMaxWait(1000*60);
		dataSource.setTimeBetweenEvictionRunsMillis(1000*60);
		dataSource.setMinEvictableIdleTimeMillis(1000*30);
		dataSource.setRemoveAbandoned(true);
		dataSource.setRemoveAbandonedTimeout(60*30);
		dataSource.setTestWhileIdle(false);
		dataSource.setFilters("stat");
		return dataSource;
	}
	
	//配置实体工厂
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws SQLException {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(druidDataSource());
		entityManagerFactory.setJpaVendorAdapter(hibernateJpaVendorAdapter());
		entityManagerFactory.setPackagesToScan("com.zaumal.test.mvc.initializer.entity");
		Map<String,Object> jpaProperties = new HashMap<String,Object>();
		jpaProperties.put("hibernate.show_sql", true);
		jpaProperties.put("hibernate.format_sql", true);
		jpaProperties.put("hibernate.autoReconnect", true);
		jpaProperties.put("hibernate.autoReconnectForPools", true);
		jpaProperties.put("hibernate.is-connection-validation-required", true);
		jpaProperties.put("hibernate.dialect.Dialect", "org.hibernate.dialect.MySQL57Dialect");
		entityManagerFactory.setJpaPropertyMap(jpaProperties);
		return entityManagerFactory;
	}
	
	@Bean
	public HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		return hibernateJpaVendorAdapter;
	}
	
	//配置事务
	@Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        return jpaTransactionManager;
    }
}
