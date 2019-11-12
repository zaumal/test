package com.zaumal.test.xml.classPathXmlApplicationContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zaumal.test.xml.classPathXmlApplicationContext.entity.People;

public class TestMain {
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("application.xml");
		
		People people = ac.getBean(People.class);
		
		System.out.println(people.getName() + " : " + people.getAge());
	}
}
