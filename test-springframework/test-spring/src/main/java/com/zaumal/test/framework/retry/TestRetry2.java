package com.zaumal.test.framework.retry;

import java.util.Random;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import com.zaumal.test.framework.retry.config.RetryConfig;

@Service
public class TestRetry2 {
	public static void main(String[] args) throws Exception {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(RetryConfig.class);
		
		TestRetry2 tr2 = ac.getBean(TestRetry2.class);
		
		int result = tr2.t1(0);
		
		System.out.println("final result: " + result);
		
		ac.close();
	}
	
	@Recover
	public int t2(Exception e,int count) {
		 System.out.println("after retry: " + count + ", recovery method called!");
         return Integer.MAX_VALUE;
	}
	
	@Retryable
	public int t1(int count) throws Exception {
		System.out.println("retry count: " + count++);
        return len();
	}
	
	private int len() throws Exception {
		Random random = new Random();
		int n = random.nextInt(10);
        if (n < 5) throw new Exception(n + " le 5");
        return n;
    }
}
