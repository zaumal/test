package com.zaumal.test.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

/**
 * 
 * parallelStream 默认使用 ForkJoinPool.commonPool()，这是一个公用的线程池，被整个程序所使用。 
 * 建议使用自定义线程池
 * 
 */
public class TestParallelStream {
	static List<Integer> list = new ArrayList<Integer>();
	static {
		for(int i = 0; i < 50; i++){
			list.add(i);
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
//		t1();
		
		t2();
	}
	
	public static void t1() throws InterruptedException {
		list.parallelStream().forEach(x -> System.out.println(x + " >> " + Thread.currentThread().getName()));
	}
	
	public static void t2() throws InterruptedException {
		ForkJoinPool pool = new ForkJoinPool(4);
		
		pool.submit(() -> list.parallelStream().forEach(x -> System.out.println(x + " >> " + Thread.currentThread().getName())));
		
		Thread.sleep(2000);
	}
}
