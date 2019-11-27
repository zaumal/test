package com.zaumal.test.xml.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		People people = ac.getBean(People.class);
		
		System.out.println(people.toString());
		
		((ConfigurableApplicationContext)ac).close();
	}
}
