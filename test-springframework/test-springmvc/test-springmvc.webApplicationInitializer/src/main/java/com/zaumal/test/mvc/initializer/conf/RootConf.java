package com.zaumal.test.mvc.initializer.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.zaumal.test.mvc.initializer.entity.People;

@Configuration
@ComponentScan("com.zaumal.test.mvc.initializer.service")
public class RootConf {
	@Bean
	public People people() {
		People people = new People();
		people.setAge(12);
		return people;
	}
}
