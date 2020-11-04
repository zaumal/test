package com.zaumal.test.devtools.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zaumal.test.devtools.service.Test1Service;
import com.zaumal.test.devtools.service.Test2Service;

@RestController
public class TestController {
	private final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	private Test1Service test1Service;
	@Autowired
	private Test2Service test2Service;
	
	@GetMapping("/")
	public String t1() {
		logger.debug("TestController t1() debug ");
		logger.info("TestController t1() info ");
		logger.warn("TestController t1() warn ");
		logger.error("TestController t1() error ");
		test1Service.test();
		test2Service.test();
		return "t1 " + UUID.randomUUID().toString();
	}
}
