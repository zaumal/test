package com.zaumal.test.generel;

public class FinalDemo {
	final int i;
	static FinalDemo finalDemo;
	public FinalDemo() {
		i = 0;
		
		new MyThread(this).start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("=============");
		finalDemo = this;
	}
	
	public static void main(String[] args) {
		FinalDemo fd = new FinalDemo();
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
}

class MyThread extends Thread{
	private FinalDemo finalDemo;
	public MyThread(FinalDemo finalDemo) {
		this.finalDemo = finalDemo;
	}
	
	@Override
	public void run() {
		System.out.println(finalDemo.i);
	}
}
