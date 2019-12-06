package com.zaumal.test.aop.advice;

import java.util.stream.Stream;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TestAdvice {
	private static final String pointcut = "execution (* com.zaumal.test.aop.service.TestService.*(..) )";
	
	@Around(pointcut)
	public void testAround(ProceedingJoinPoint joinPoint) {
		Object[] objs = joinPoint.getArgs();
		
		System.out.println("around befor");
		
		try {
			joinPoint.proceed(objs);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		System.out.println("around after");
		
		
		Stream.of(objs).forEach(obj -> System.out.print(obj + " "));
	}
}
