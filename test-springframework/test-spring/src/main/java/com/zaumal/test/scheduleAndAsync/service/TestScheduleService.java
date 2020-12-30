package com.zaumal.test.scheduleAndAsync.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TestScheduleService {
	private int i = 0;
	@Scheduled(cron = "0/3 * * * * ? ")
	public void t() {
		System.out.println("@Scheduled " + Thread.currentThread().getName() + "TestScheduleService t " + i++);
	}
}
