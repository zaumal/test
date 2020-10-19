package com.zaumal.test.springboot.starter.hello.service;

import com.zaumal.test.springboot.starter.hello.properties.HelloProperties;

public class HelloService {
	private String uuid;
	private HelloProperties helloProperties;
	
	public HelloService(String uuid,HelloProperties helloProperties) {
		this.uuid = uuid;
		this.helloProperties = helloProperties;
	}
	
	public void helloWorld() {
		System.out.println("[" + uuid + "] - name : " + helloProperties.getName() + " - age : " + helloProperties.getAge());
	}
}
