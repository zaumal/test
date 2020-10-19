package com.zaumal.test.springboot.starter.demo.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zaumal.test.springboot.starter.hello.service.HelloService;

@Service
public class DemoService {
	@Autowired
	private HelloService helloService;
	
	public String t1() {
		helloService.helloWorld();
		return UUID.randomUUID().toString();
	}
}
