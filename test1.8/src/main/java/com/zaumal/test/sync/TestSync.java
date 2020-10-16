package com.zaumal.test.sync;

public class TestSync{
	public void test1(TestSync t) {
		synchronized (t) {
			System.out.println(Thread.currentThread().getName() + " " + t.toString() + " synchronized 代码块");
			t();
		}
	}
	
	public synchronized void test2(TestSync t) {
		System.out.println(Thread.currentThread().getName() + " "+t.toString() + " synchronized 非静态方法");
		t();
	}
	
	public synchronized static void test3(TestSync t) {
		System.out.println(Thread.currentThread().getName() + " "+t.toString() + " synchronized 静态方法");
		t();
	}
	
	public void test4(TestSync t) {
		synchronized (TestSync.class) {
			System.out.println(Thread.currentThread().getName() + " "+t.toString() + " synchronized class对象");
			t();
		}
	}
	
	public static void t() {
		try {
			Thread.sleep(1000*5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void test5(TestSync t) {
		System.out.println(Thread.currentThread().getName() + " "+t.toString() + " 无 synchronized");
	}
	
	public static void main(String[] args) {
		TestSync tt1 = new TestSync();
		TestSync tt2 = new TestSync();
		
		//sync 代码块，锁（）中的对象
//		TestThread1 t1 = new TestThread1(tt1);
//		TestThread1 t2 = new TestThread1(tt2);
//		TestThread1 t3 = new TestThread1(tt1);
//		
//		t1.start();
//		t2.start();
//		t3.start();
		
		//sync 非静态方法，锁调用方法的对象
//		TestThread2 t1 = new TestThread2(tt1);
//		TestThread2 t2 = new TestThread2(tt2);
//		TestThread2 t3 = new TestThread2(tt1);
//		
//		t1.start();
//		t2.start();
//		t3.start();
		
		//sync 静态方法，锁类的class对象，也就是阻塞所有该类对象
//		TestThread3 t1 = new TestThread3(tt1);
//		TestThread3 t2 = new TestThread3(tt2);
//		TestThread3 t3 = new TestThread3(tt1);
//		
//		t1.start();
//		t2.start();
//		t3.start();
		
		//锁类的class对象
		TestThread4 t1 = new TestThread4(tt1);
		TestThread4 t2 = new TestThread4(tt2);
		TestThread4 t3 = new TestThread4(tt1);
		
		t1.start();
		t2.start();
		t3.start();
	}
}
class TestThread1 extends Thread{
	private TestSync t;
	public TestThread1(TestSync t) {
		this.t = t;
	}
	@Override
	public void run() {
		t.test1(t);
	}
}

class TestThread2 extends Thread{
	private TestSync t;
	public TestThread2(TestSync t) {
		this.t = t;
	}
	@Override
	public void run() {
		t.test2(t);
	}
}

class TestThread3 extends Thread{
	private TestSync t;
	public TestThread3(TestSync t) {
		this.t = t;
	}
	@Override
	public void run() {
		t.test3(t);
	}
}

class TestThread4 extends Thread{
	private TestSync t;
	public TestThread4(TestSync t) {
		this.t = t;
	}
	@Override
	public void run() {
		t.test4(t);
	}
}

class TestThread5 extends Thread{
	private TestSync t;
	public TestThread5(TestSync t) {
		this.t = t;
	}
	@Override
	public void run() {
		t.test5(t);
	}
}