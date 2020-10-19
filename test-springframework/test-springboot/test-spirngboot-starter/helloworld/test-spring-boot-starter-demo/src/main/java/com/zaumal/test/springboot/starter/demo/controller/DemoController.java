package com.zaumal.test.springboot.starter.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zaumal.test.springboot.starter.demo.service.DemoService;

@RestController
@RequestMapping("/demo")
public class DemoController {
	@Autowired
	private DemoService demoService;
	
	@GetMapping("/t1")
	public String test1() {
		return demoService.t1();
	}
}
