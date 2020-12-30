package com.zaumal.test.wf.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class TestService {
	public String sleepThenReturn(int s) {
		try {
			Thread.sleep(s*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("TestService sleepThenReturn");
		return UUID.randomUUID().toString();
	}
}
