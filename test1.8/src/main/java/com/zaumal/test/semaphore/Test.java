package com.zaumal.test.semaphore;

import java.util.concurrent.Semaphore;

public class Test {
	public static void main(String[] args) throws InterruptedException {
		SemaphoreService semaphoreService = new SemaphoreService(1);
		MyThread myThread1 = new MyThread(semaphoreService);
//		MyThread myThread2 = new MyThread(semaphoreService);
//		MyThread myThread3 = new MyThread(semaphoreService);
//		MyThread myThread4 = new MyThread(semaphoreService);
//		MyThread myThread5 = new MyThread(semaphoreService);
//		MyThread myThread6 = new MyThread(semaphoreService);
		
		myThread1.start();
//		myThread2.start();
//		myThread3.start();
//		myThread4.start();
//		myThread5.start();
//		myThread6.start();
		
		
		Thread.sleep(3000);
		myThread1.interrupt();
	}
}

class MyThread extends Thread{
	private SemaphoreService semaphoreService;
	
	public MyThread(SemaphoreService semaphoreService) {
		this.semaphoreService = semaphoreService;
	}
	
	@Override
	public void run() {
		try {
			this.semaphoreService.doSoething();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class SemaphoreService{
	private int i = 0;
	private Semaphore semaphore;
	
	public SemaphoreService(int n) {
		this.semaphore = new Semaphore(n);
	}
	
	public void doSoething() throws InterruptedException {
		while(true) {
//			Thread.sleep(1000);
			semaphore.acquire();
			System.out.println(Thread.currentThread().getName() + " : " + i++);
//			Thread.sleep(1000);
			semaphore.release();
		}
	}
}