package com.zaumal.test.demo1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zaumal.test.demo1.feign.clien.Demo2Clien;

@RestController
@RequestMapping("/demo1")
public class Demo1Controller {
	@Autowired
	private Demo2Clien demo2Clien;
	@Value("${test.v1}")
	private String v1;
	
	@GetMapping("/t1/{p1}")
	public String t1(@PathVariable String p1) {
		System.out.println("配置中心配置文件中的参数：v1 = " + v1);
		return "demo1 > t1 > " + p1;
	}
	
	@GetMapping("/t2/{p1}")
	public String t2(@PathVariable String p1) {
		return "demo1 > t2 > " + demo2Clien.t2(p1);
	}
	
	@GetMapping("/t3/{p1}")
	public String t3(@PathVariable String p1) {
		return "dmeo1 > t3 > " + demo2Clien.t3(p1);
	}
}
