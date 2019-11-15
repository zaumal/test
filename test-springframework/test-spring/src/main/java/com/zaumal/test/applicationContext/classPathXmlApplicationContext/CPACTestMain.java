package com.zaumal.test.applicationContext.classPathXmlApplicationContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zaumal.test.applicationContext.classPathXmlApplicationContext.entity.People;

public class CPACTestMain {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("application.xml");
		System.out.println("=================");
		People people = ac.getBean(People.class);
		
		System.out.println(people.getName() + " : " + people.getAge());
	}
}
