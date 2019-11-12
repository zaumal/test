package com.zaumal.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {
	public static void main(String[] args) {
		Subject subject = new RealSubject();
		Subject proxy = new DynamicProxy(subject).getProxy();
		proxy.doSomething();
	}
}

class DynamicProxy implements InvocationHandler{
	private Subject subject;
	
	public DynamicProxy(Subject subject) {
		this.subject = subject;
	}
	
	public Subject getProxy() {
        return (Subject) Proxy.newProxyInstance(subject.getClass().getClassLoader(), subject.getClass().getInterfaces(), this);
    }

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("before...");
		
		method.invoke(this.subject, args);
		
		System.out.println("after...");
		return null;
	}
}

interface Subject{
	void doSomething();
}

class RealSubject implements Subject{
	@Override
	public void doSomething() {
		System.out.println("RealSubject doSomething");
	}
}