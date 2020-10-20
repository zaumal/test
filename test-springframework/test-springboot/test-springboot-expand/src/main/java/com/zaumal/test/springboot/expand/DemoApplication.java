package com.zaumal.test.springboot.expand;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.zaumal.test.springboot.expand.listener.event.MyApplicationEvent;

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		ApplicationContext ac = SpringApplication.run(DemoApplication.class, args);

		System.out.println("=================================== EVENT ========================================");
		
		MyApplicationEvent myEvent = new MyApplicationEvent("springboot测试", "测试自定义application事件");
		
		ac.publishEvent(myEvent);
	}
}
