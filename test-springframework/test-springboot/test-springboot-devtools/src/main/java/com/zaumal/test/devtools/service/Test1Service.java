package com.zaumal.test.devtools.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class Test1Service {
	private final Logger logger = LoggerFactory.getLogger(Test1Service.class);

	public String test() {
		logger.debug("Test1Service test() debug ");
		logger.info("Test1Service test() info ");
		logger.warn("Test1Service test() warn ");
		logger.error("Test1Service test() error ");
		return "Test1Service test()";
	}
}
