package com.zaumal.test.mylog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zaumal.test.mylog.service.DemoService;
import com.zaumal.test.starter.log.MyLog;

@RestController
@RequestMapping("/demo")
public class DemoController {
	@Autowired
	private DemoService demoService;
	
	@MyLog(desc = "测试1")
	@GetMapping("/test1/{v}")
	public String test1(@PathVariable String v) {
		return demoService.test1(v);
	}
	
	@MyLog
	@GetMapping("/test2/{v}")
	public String test2(@PathVariable String v) {
		return demoService.test1(v);
	}
}
