package com.zaumal.test.lambda;

public class Test1 {
	public static void main(String[] args) {
		Runnable r = () -> System.out.println("test");
		new Thread(r).start();
		
		new Thread(() -> System.out.println("test2")).start();
		
		IUserCap iu = n -> System.out.println(n + 1);
		iu.t1(3);
	}
}

