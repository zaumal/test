package com.zaumal.test.springboot.expand.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class MyApplicationListener1 implements ApplicationListener<ContextRefreshedEvent> {
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>> MyApplicationListener1 onApplicationEvent(ContextRefreshedEvent event)");
	}
}
