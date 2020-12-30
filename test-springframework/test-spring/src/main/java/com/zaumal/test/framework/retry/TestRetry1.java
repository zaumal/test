package com.zaumal.test.framework.retry;

import java.util.Random;

import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

public class TestRetry1 {
	public static void main(String[] args) throws Exception {
		TestRetry1 rt = new TestRetry1();
		rt.t1();
	}
	
	public void t1() throws Exception {
		RetryTemplate retryTemplate = new RetryTemplate();

        SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();
        simpleRetryPolicy.setMaxAttempts(3); //最多重试3次

        retryTemplate.setRetryPolicy(simpleRetryPolicy);

        Integer result = retryTemplate.execute(new RetryCallback<Integer, Exception>() {
            // 重试操作
            @Override
            public Integer doWithRetry(RetryContext retryContext) throws Exception {
                System.out.println("retry count: " + retryContext.getRetryCount());
                return len();
            }
        }, new RecoveryCallback<Integer>() { //兜底回调
            @Override
            public Integer recover(RetryContext retryContext) throws Exception {
                System.out.println("after retry: " + retryContext.getRetryCount() + ", recovery method called!");
                return Integer.MAX_VALUE;
            }
        });
        
        System.out.println("final result: " + result);
	}
	
	private int len() throws Exception {
		Random random = new Random();
		int n = random.nextInt(10);
        if (n < 5) throw new Exception(n + " le 5");
        return n;
    }
}
