package com.zaumal.test.scheduleAndAsync.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableAsync
@EnableScheduling
@ComponentScan("com.zaumal.test.scheduleAndAsync.service")
public class ScheduleAndAsyncConfig {
	@Bean
	public ExecutorService executorService() {
		return Executors.newFixedThreadPool(5);
	}
}