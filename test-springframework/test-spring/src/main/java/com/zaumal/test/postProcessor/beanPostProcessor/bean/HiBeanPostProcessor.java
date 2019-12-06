package com.zaumal.test.postProcessor.beanPostProcessor.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class HiBeanPostProcessor implements BeanPostProcessor {
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("postProcessBeforeInitialization ... [" + beanName + "]");
		return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
	}
	
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("postProcessAfterInitialization ... [" + beanName + "]");
		return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
	}
}
