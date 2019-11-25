package com.zaumal.test.atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Synchronized_VS_CAS {
	public static void main(String[] args) {
//		puls(100);
//		cas(100);
		sync(10);
	}
	
	public static void puls(int k) {
		Long start = System.currentTimeMillis();
		int n = 1;
		List<Thread> ts = new ArrayList<>();
		for(int i = 0; i < k; i++) {
			Thread t = new Thread(new SampleThread(n));
			ts.add(t);
			t.start();
		}
		ts.forEach(t -> {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		Long end = System.currentTimeMillis();
		System.out.println("cas time : " + (end - start));
	}
	
	public static void cas(int k) {
		Long start = System.currentTimeMillis();
		AtomicInteger n = new AtomicInteger(1);
		List<Thread> ts = new ArrayList<>();
		for(int i = 0; i < k; i++) {
			Thread t = new Thread(new CasThread(n));
			ts.add(t);
			t.start();
		}
		ts.forEach(t -> {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		Long end = System.currentTimeMillis();
		System.out.println("cas time : " + (end - start));
	}
	
	public static void sync(int k) {
		Long start = System.currentTimeMillis();
		Integer n = 1;
		List<Thread> ts = new ArrayList<>();
		for(int i = 0; i < k; i++) {
			Thread t = new Thread(new SyncThread(n));
			ts.add(t);
			t.start();
		}
		ts.forEach(t -> {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		Long end = System.currentTimeMillis();
		System.out.println("sync time : " + (end - start));
	}
}

class SampleThread implements Runnable{
	private int n;
	
	public SampleThread(int n) {
		this.n = n;
	}
	
	@Override
	public void run() {
		while(this.n <= 100000000) {
			plusOne();
		}
	}
	
	public void plusOne() {
		n++;
	}
}

class CasThread implements Runnable{
	private AtomicInteger n;
	
	public CasThread(AtomicInteger n) {
		this.n = n;
	}
	
	@Override
	public void run() {
		while(this.n.get() <= 100000000) {
			plusOne();
		}
	}
	
	public void plusOne() {
//		System.out.println(Thread.currentThread().getName() + " : " + n.addAndGet(1));
		n.addAndGet(1);
	}
}

class SyncThread implements Runnable{
	private Integer n;
	
	public SyncThread(Integer n) {
		this.n = n;
	}
	
	@Override
	public void run() {
		while(this.n <= 100) {
			synchronized (n) {
				System.out.println(Thread.currentThread().getName() + " : " + n);
				this.n++;
			}
		}
	}
}