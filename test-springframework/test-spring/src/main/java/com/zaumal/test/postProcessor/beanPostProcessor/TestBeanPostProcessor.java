package com.zaumal.test.postProcessor.beanPostProcessor;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaumal.test.postProcessor.beanPostProcessor.bean.HiBeanPostProcessor;
import com.zaumal.test.postProcessor.beanPostProcessor.bean.People;

@Configuration
public class TestBeanPostProcessor {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext(TestBeanPostProcessor.class);
		acac.getBean(People.class);
		acac.close();
	}
	
	@Bean(initMethod = "initMethod",destroyMethod = "destroyMethod")
	public People people() {
		return new People();
	}
	
	@Bean
	public HiBeanPostProcessor hiBeanPostProcessor() {
		return new HiBeanPostProcessor();
	}
}
