package com.zaumal.test.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 
 * CompletableFuture 默认使用 ForkJoinPool.commonPool() 线程池，
 * 值得注意的是，commonPool 中都是守护线程，主线程执行完，子线程也就over了。
 * 因此建议当任务非常耗时，使用自定义线程池。
 * 
 */
public class TestCompletableFuture {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService exe = Executors.newCachedThreadPool();
		
		CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
			sleep(8000);
			System.out.println(1);
		}).thenAcceptAsync(n -> {
			sleep(6000);
			System.out.println(2);
		}).thenRunAsync(() -> {
			sleep(4000);
			System.out.println(3);
		},exe).thenRunAsync(() -> {
			sleep(2000);
			System.out.println(4);
		},exe).whenCompleteAsync((a,b) -> {
			sleep(2000);
			System.out.println(5);
		},exe);
		System.out.println(6);
		System.out.println("count1 : " + ((ThreadPoolExecutor)exe).getActiveCount());
//		cf.get();
		System.out.println(cf.isDone());
		sleep(20000);
		System.out.println("count2 : " + ((ThreadPoolExecutor)exe).getActiveCount());
		System.out.println(7);
//		exe.shutdown();
		System.out.println(8);
	}
	
	public static void sleep(int n) {
		try {
			Thread.sleep(n);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
