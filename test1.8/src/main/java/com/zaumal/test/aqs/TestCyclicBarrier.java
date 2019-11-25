package com.zaumal.test.aqs;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class TestCyclicBarrier {
	public static void main(String[] args) {
		CyclicBarrier ready =  new CyclicBarrier(10);
		for(int i = 0; i < 10; i++) {
			Thread t = new Worker(ready);
			t.start();
		}
		System.out.println(Thread.currentThread().getName() + "等待");
		
		//重置
		ready.reset();
	}
}

class Worker extends Thread{
	private CyclicBarrier ready;
	
	public Worker(CyclicBarrier ready) {
		this.ready = ready;
	}
	
	@Override
	public void run() {
		int t = new Random().nextInt(1000);
		
		try {
			System.out.println(Thread.currentThread().getName() + " 准备 " + t + " 毫秒");
			Thread.sleep(t);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			System.out.println(Thread.currentThread().getName() + " 准备完成");
			ready.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread().getName() + " 执行");
	}
}