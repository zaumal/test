package com.zaumal.test.mvc.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zaumal.test.mvc.entity.People;

@Service
public class TestService {
	@Autowired
	private People people;
	
	public String get() {
		return people.getName() + " : " + UUID.randomUUID().toString();
	}

}
