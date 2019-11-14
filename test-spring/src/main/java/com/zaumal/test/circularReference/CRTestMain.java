package com.zaumal.test.circularReference;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zaumal.test.circularReference.conf.AppConfig;
import com.zaumal.test.circularReference.service.UserService;

public class CRTestMain {
	public static void main(String[] args) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		System.out.println("==================");
		UserService userService = ac.getBean(UserService.class);
	}
}
