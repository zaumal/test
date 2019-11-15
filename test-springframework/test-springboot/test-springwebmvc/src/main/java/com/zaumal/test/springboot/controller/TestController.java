package com.zaumal.test.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zaumal.test.springboot.service.TestService;

@RestController
public class TestController {
	@Autowired
	private TestService testService;
	
	@GetMapping
	public String test() {
		return testService.get();
	}
}
