package com.zaumal.test.springboot.expand.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class MySpringApplicationRunListener implements SpringApplicationRunListener {
	private final String name = ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> MySpringApplicationRunListener : ";
	private final SpringApplication application;
	private final String[] args;
	
	public MySpringApplicationRunListener(SpringApplication application,String[] args) {
		this.application = application;
		this.args = args;
	}
	
	@Override
	public void starting() {
		System.out.println(name + "starting - application : " + application);
		System.out.println(name + "starting - args : " + args);
	}

	@Override
	public void environmentPrepared(ConfigurableEnvironment environment) {
		System.out.println(name + "environmentPrepared(ConfigurableEnvironment environment)");
	}

	@Override
	public void contextPrepared(ConfigurableApplicationContext context) {
		System.out.println(name + "contextPrepared(ConfigurableApplicationContext context)");
	}

	@Override
	public void contextLoaded(ConfigurableApplicationContext context) {
		System.out.println(name + "contextLoaded(ConfigurableApplicationContext context)");
	}

	@Override
	public void started(ConfigurableApplicationContext context) {
		System.out.println(name + "started(ConfigurableApplicationContext context)");
	}

	@Override
	public void running(ConfigurableApplicationContext context) {
		System.out.println(name + "running(ConfigurableApplicationContext context)");
	}

	@Override
	public void failed(ConfigurableApplicationContext context, Throwable exception) {
		System.out.println(name + "failed(ConfigurableApplicationContext context, Throwable exception)");
	}

}
