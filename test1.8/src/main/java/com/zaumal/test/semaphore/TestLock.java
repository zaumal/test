package com.zaumal.test.semaphore;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class TestLock {
	public static void main(String[] args) {
//		test1(1);
		test1(3);
	}
	
	public static void test1(int n) {
		Output o = new Output(n);
		for(int i = 0; i < 10; i++) {
			new Thread(() -> {
				o.output();
			}).start();
		}
	}
}

class Output{
	Semaphore s;
	
	public Output(int n) {
		s = new Semaphore(n);
	}
	
	public void output() {
		try {
			s.acquire();
			System.out.println(Thread.currentThread().getName() + " 开始");
			int t = new Random().nextInt(1000);
			Thread.sleep(t);
			System.out.println(Thread.currentThread().getName() + " 结束");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			s.release();
		}
	}
}