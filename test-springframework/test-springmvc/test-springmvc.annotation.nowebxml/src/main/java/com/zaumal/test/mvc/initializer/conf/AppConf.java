package com.zaumal.test.mvc.initializer.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaumal.test.mvc.initializer.entity.People;

@Configuration
public class AppConf {
	@Bean
	public People people() {
		People people = new People();
		people.setAge(11);
		return people;
	}
}
