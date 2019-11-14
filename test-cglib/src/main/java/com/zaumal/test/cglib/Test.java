package com.zaumal.test.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class Test {
	public static void main(String[] args) {
		t2();
	}
	
	public static void t2() {
		Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallbackFilter(new CallbackFilter() {
			@Override
			public int accept(Method method) {
				if(method.getName().equals("t")){
					System.out.println("filter t == 0");
					return 0;
				}
				return 1;
			}
        });
        
        SampleClass sc = (SampleClass)enhancer.create();
        sc.t();
	}
	
	public static void t1() {
		Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback(new MethodInterceptor() {
			@Override
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				System.out.println("befor...");
				
				Object result = proxy.invokeSuper(obj, args);
				
				System.out.println("after...");
				
				return result;
			}
        });
        
        SampleClass sc = (SampleClass)enhancer.create();
        sc.t();
        
	}
}

class SampleClass{
	public void t() {
		System.out.println("hi sample class");
	}
}