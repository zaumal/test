package com.zaumal.test.devtools.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class Test2Service {
	private final Logger logger = LoggerFactory.getLogger(Test2Service.class);
	
	public String test() {
		logger.debug("Test2Service test() debug ");
		logger.info("Test2Service test() info ");
		logger.warn("Test2Service test() warn ");
		logger.error("Test2Service test() error ");
		return "Test2Service test()";
	}
}
