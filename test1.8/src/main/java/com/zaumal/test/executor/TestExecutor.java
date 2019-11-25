package com.zaumal.test.executor;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestExecutor {
	public static void main(String[] args) throws Exception {
		Executors e;
		ThreadPoolExecutor tpe;
		
		Executors.newFixedThreadPool(1);
		Executors.newCachedThreadPool();
		Executors.newSingleThreadExecutor();
		Executors.newSingleThreadScheduledExecutor();
		Executors.newScheduledThreadPool(10);
		Executors.newWorkStealingPool();
		
		Callable c;
		
		test3();
		BlockingQueue bq;
		
		
	}
	
	public static void test3() throws Exception {
		long start = System.currentTimeMillis();
		Callable<Integer> callable = new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				Thread.sleep(2000);
				System.out.println("t1");
				return new Random().nextInt(100);
			}
		};
		FutureTask<Integer> ft = new FutureTask<Integer>(callable);
		Thread t1 = new Thread(ft);
		Thread t2 = new Thread() {
			public void run() {
				try {
					Thread.sleep(2000);
					System.out.println("t2");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		
		t1.start();
		t2.start();
		
		Thread.sleep(2000);
		System.out.println(ft.get());
		long end = System.currentTimeMillis();
		System.out.println((start-end)*1.0/1000.0);
	}
	
	public static void test() {
		ThreadPoolExecutor es = new ThreadPoolExecutor(5, 8,
                2000, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
		for(int i = 0; i < 13; i++) {
			test2(es);
		}
		
		for(;;) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("=====================================");
			System.out.println("ActiveCount : " + es.getActiveCount());
			System.out.println("CorePoolSize : " + es.getCorePoolSize());
			System.out.println("MaximumPoolSize : " + es.getMaximumPoolSize());
			System.out.println("PoolSize : " + es.getPoolSize());
			System.out.println("CompletedTaskCount : " + es.getCompletedTaskCount());
			System.out.println("=====================================");
		}
	}
	
	public static void test2(ThreadPoolExecutor es) {
		es.submit(() -> {
//			while(true) {
				try {
					Thread.sleep(1000);
					System.out.println(Thread.currentThread().getName());
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
//			}
		});
	}
}



