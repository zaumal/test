package com.zaumal.test.executor;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestCallable implements Callable<Integer> {
	@Override
	public Integer call() throws Exception {
		int n = new Random().nextInt(1000);
		return n;
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		TestCallable callable = new TestCallable();
		FutureTask<Integer> ft =  new FutureTask<>(callable);
		Thread t = new Thread(ft);
		
//		System.out.println(ft.get());
		t.start();
		System.out.println(ft.get());
	}
}
