package com.zaumal.test.mylog.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class DemoService {
	public String test1(String v) {
		v = UUID.randomUUID().toString() + " : " + v;
		System.out.println(v);
		return v;
	}
}
