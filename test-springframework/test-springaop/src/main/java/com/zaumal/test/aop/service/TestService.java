package com.zaumal.test.aop.service;

import org.springframework.stereotype.Service;

@Service
public class TestService {
	public void test1(String s) {
		System.out.println("do TestService test1 [" + s + "]");
	}
	
	public void test2(String s) {
		System.out.println("do TestService test2 [" + s + "]");
	}
	
	public void test3(String s) {
		System.out.println("do TestService test3 [" + s + "]");
	}
}
