package com.zaumal.test.mvc.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaumal.test.mvc.entity.People;

@Configuration
public class RootConf {
	@Bean
	public People people() {
		People people = new People();
		people.setAge(11);
		people.setName("lisi");
		return people;
	}
}
