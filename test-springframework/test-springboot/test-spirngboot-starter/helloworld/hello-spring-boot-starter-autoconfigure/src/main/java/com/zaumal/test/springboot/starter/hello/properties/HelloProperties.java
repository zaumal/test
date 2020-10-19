package com.zaumal.test.springboot.starter.hello.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("com.zaumal.test.hello")
public class HelloProperties {
	private String name;
	private int age;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
