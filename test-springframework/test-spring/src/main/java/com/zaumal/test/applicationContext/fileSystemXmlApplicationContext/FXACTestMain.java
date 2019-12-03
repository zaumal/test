package com.zaumal.test.applicationContext.fileSystemXmlApplicationContext;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.zaumal.test.applicationContext.fileSystemXmlApplicationContext.entity.People;

public class FXACTestMain {
	public static void main(String[] args) {
		String xml = "D:\\workspace\\test\\test-springframework\\test-spring\\src\\main\\resources\\fileSystemXmlapplicationContext.xml";
		ConfigurableApplicationContext ac = new FileSystemXmlApplicationContext(xml);
		People people = ac.getBean(People.class);
		System.out.println(people.toString());
		ac.close();
	}
}
