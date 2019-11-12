package com.zaumal.test.xml.annotationConfigApplicationContext.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaumal.test.xml.annotationConfigApplicationContext.entity.People;

@Configuration
public class AppConfig {
	@Bean(name = "people")
	public People entitlement() {
		People people = new People();
		people.setName("lisi");
		people.setAge(13);
		return people;
	}
}