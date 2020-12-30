package com.zaumal.test.scheduleAndAsync.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class TestAsyncService {
	@Async("executorService")
	public void t() {
		for(int i = 0; i < 10; i++) {
			sleep();
			System.out.println("@Async " + Thread.currentThread().getName() + "TestAsyncService t " + i);
		}
	}
	
	public void sleep() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
