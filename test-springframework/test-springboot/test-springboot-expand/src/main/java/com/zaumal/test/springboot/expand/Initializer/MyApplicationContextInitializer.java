package com.zaumal.test.springboot.expand.Initializer;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.web.context.ConfigurableWebApplicationContext;

public class MyApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableWebApplicationContext> {
	@Override
	public void initialize(ConfigurableWebApplicationContext applicationContext) {
		System.out.println(">>>>>>>>>>> MyApplicationContextInitializer initialize(ConfigurableWebApplicationContext applicationContext)");
	}
}
