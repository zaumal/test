package com.zaumal.test.mybatis.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicatoinContextUtil implements ApplicationContextAware {
	private static ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ApplicatoinContextUtil.applicationContext = applicationContext;
	}
	
	public static ApplicationContext getApplicationContext() {
		return ApplicatoinContextUtil.applicationContext;
	}

	public static <T> T getBean(Class<T> clazz) {
		return ApplicatoinContextUtil.applicationContext.getBean(clazz);
	}
}
