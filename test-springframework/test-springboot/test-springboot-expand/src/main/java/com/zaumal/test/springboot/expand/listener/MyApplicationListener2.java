package com.zaumal.test.springboot.expand.listener;

import org.springframework.context.ApplicationListener;

import com.zaumal.test.springboot.expand.listener.event.MyApplicationEvent;

public class MyApplicationListener2 implements ApplicationListener<MyApplicationEvent> {
	@Override
	public void onApplicationEvent(MyApplicationEvent event) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>> MyApplicationListener2 onApplicationEvent(MyApplicationEvent event) : " + event.getMy());
		event.myEvent();
	}
}
