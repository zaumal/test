package com.zaumal.test.factoryBean.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaumal.test.factoryBean.factoryBean.PeopleFactoryBean;

@Configuration
public class AppConf {
	@Bean
	public PeopleFactoryBean peopleFactoryBean() {
		return new PeopleFactoryBean("liwu,23");
	}
}
