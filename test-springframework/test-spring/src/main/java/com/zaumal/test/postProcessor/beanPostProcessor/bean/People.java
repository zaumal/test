package com.zaumal.test.postProcessor.beanPostProcessor.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class People implements InitializingBean,DisposableBean{
	private String id;
	private int age;
	private String name;
	public People() {
		System.out.println("people 构造函数");
	}
	
	@PostConstruct
	public void postConstruct() {
		System.out.println("@PostConstruct");
	}
	public void initMethod() {
		System.out.println("initMethod");
	}
	
	@PreDestroy
	public void preDestroy() {
		System.out.println("@PreDestroy");
	}
	public void destroyMethod() {
		System.out.println("destroyMethod");
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("DisposableBean destroy");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("InitializingBean afterPropertiesSet");
	}
}
