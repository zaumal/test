package com.zaumal.test.scheduleAndAsync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zaumal.test.scheduleAndAsync.config.MySchedulingConfigurer;
import com.zaumal.test.scheduleAndAsync.config.ScheduleAndAsyncConfig;
import com.zaumal.test.scheduleAndAsync.service.TestAsyncService;

public class TestScheduleMain {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("begin");
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ScheduleAndAsyncConfig.class,MySchedulingConfigurer.class);
		
		TestAsyncService testAsyncService = ac.getBean(TestAsyncService.class);
		
		testAsyncService.t();

		ExecutorService pool1 = ac.getBean("executorService",ExecutorService.class);
		ScheduledThreadPoolExecutor pool2 = ac.getBean("taskExecutor",ScheduledThreadPoolExecutor.class);
		
		while(true){
			Thread.sleep(5000);
			if(pool1.isShutdown() && pool2.isShutdown()) {
				ac.close();
				break;
			}
		}
		System.out.println("end");
	}
}
