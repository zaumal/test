package com.zaumal.test.mvc.initializer.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.zaumal.test.mvc.initializer.entity.People;

@Service
public class TestService {
	@Autowired
	private ApplicationContext ac;
	
	public String get() {
		People people = ac.getBean(People.class);
		return people.getName() + " " + people.getAge() + " - " +  UUID.randomUUID().toString();
	}
}
