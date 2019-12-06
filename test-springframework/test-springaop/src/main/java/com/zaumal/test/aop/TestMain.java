package com.zaumal.test.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zaumal.test.aop.conf.AppConf;
import com.zaumal.test.aop.service.TestService;

public class TestMain {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext();
		acac.register(AppConf.class);
		acac.refresh();
		
		TestService ts = acac.getBean(TestService.class);
		
		ts.test1("t1");
		
		System.out.println("================");
		acac.close();
		
		ts.test2("t2");
	}
}
