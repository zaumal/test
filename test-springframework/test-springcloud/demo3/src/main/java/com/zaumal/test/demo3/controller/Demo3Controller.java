package com.zaumal.test.demo3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo3")
public class Demo3Controller {
	@GetMapping("/t1/{p1}")
	public String t1(@PathVariable String p1) {
		return "demo3 > t1 > " + p1;
	}
	
	@GetMapping("/t2/{p1}")
	public String t2(@PathVariable String p1) {
		return "demo3 > t2 > " + p1;
	}
	
	@GetMapping("/t3/{p1}")
	public String t3(@PathVariable String p1) {
		return "demo3 > t3 > " + p1 + " >< [" + this + "]";
	}
}
