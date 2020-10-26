package com.zaumal.test.demo2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zaumal.test.demo2.fegin.client.Demo3Client;

@RestController
@RequestMapping("/demo2")
public class Demo2Controller {
	@Autowired
	private Demo3Client demo3Client;
	
	@GetMapping("/t1/{p1}")
	public String t1(@PathVariable String p1) {
		return "demo2 > t1 > " + p1;
	}
	
	@GetMapping("/t2/{p1}")
	public String t2(@PathVariable String p1) {
		return "demo2 > t2 > " + p1;
	}
	
	@GetMapping("/t3/{p1}")
	public String t3(@PathVariable String p1) {
		System.out.println("demo2 t3 > ");
		return "demo2 > t3 > " + demo3Client.t3(p1);
	}
}
