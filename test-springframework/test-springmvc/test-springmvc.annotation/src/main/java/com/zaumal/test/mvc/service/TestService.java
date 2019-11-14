package com.zaumal.test.mvc.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.zaumal.test.mvc.entity.People;

@Service
public class TestService {
	@Autowired
	private ApplicationContext applicationContext;
	
	public String get() {
		People people = applicationContext.getBean(People.class);
		return people.getName() + " : " + people.getAge() + " annotation : " + UUID.randomUUID().toString();
	}
}