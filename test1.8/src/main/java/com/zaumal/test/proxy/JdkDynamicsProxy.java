package com.zaumal.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkDynamicsProxy implements InvocationHandler {
	private Object target;
	
	public JdkDynamicsProxy(Object target) {
		this.target = target;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getProxy() {
		return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), 
				target.getClass().getInterfaces(), this);
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("do before");
		Object result = method.invoke(target, args);
		System.out.println("do after");
		return result;
	}

}
