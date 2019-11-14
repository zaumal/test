package com.zaumal.test.applicationContext.classPathXmlApplicationContext.entity;

public class People {
	private String name;
	private int age;
	
	public People() {
		System.out.println("People 构造方法...");
	}
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
