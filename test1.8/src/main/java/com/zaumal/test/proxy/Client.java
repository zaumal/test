package com.zaumal.test.proxy;

public class Client {
	public static void main(String[] args) {
		Subject subject = new JdkDynamicsProxy(new RealSubject()).getProxy();
		subject.doSomething();
	}
}
