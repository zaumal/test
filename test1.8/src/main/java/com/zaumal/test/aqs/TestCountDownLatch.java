package com.zaumal.test.aqs;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class TestCountDownLatch {
	public static void main(String[] args) {
		CountDownLatch ready = new CountDownLatch(10);
		CountDownLatch start = new CountDownLatch(1);
		
		for(int i = 0; i < 10; i++) {
			Thread t = new Runner(ready,start);
			t.start();
		}
		
		try {
			System.out.println(Thread.currentThread().getName() + " 等待准备");
			ready.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread().getName() + " 发令");
		
		start.countDown();
		
	}
}

class Runner extends Thread{
	private CountDownLatch ready;
	private CountDownLatch start;

	public Runner(CountDownLatch ready,CountDownLatch start) {
		this.ready = ready;
		this.start = start;
	}
	
	@Override
	public void run() {
		int t = new Random().nextInt(1000);
		
		try {
			System.out.println(Thread.currentThread().getName() + " 准备  " + t + " 毫秒");
			Thread.sleep(t);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread().getName() + " 准备完成");
		
		ready.countDown();
		
		try {
			start.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread().getName() + " 执行 ");
	}
}