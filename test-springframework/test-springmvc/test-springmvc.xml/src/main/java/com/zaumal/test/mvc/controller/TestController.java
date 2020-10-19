package com.zaumal.test.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zaumal.test.mvc.service.TestService;

@RestController
public class TestController {
	@Autowired
	private TestService testService;
	
	@GetMapping("/test")
	public String test() {
		return testService.get();
	}
	
	@GetMapping("/test2")
	public String test2() {
		return "test2";
	}
}
