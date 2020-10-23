package com.zaumal.test.classLoad;

public class TestClassLoader {
	public static void main(String[] args) {
		ClassLoader c1 = TestClassLoader.class.getClassLoader();
		
		ClassLoader c2 = c1.getParent();
		ClassLoader c3 = c2.getParent();
		
		ClassLoader c4 = ClassLoader.getSystemClassLoader();
		
		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c3);
		System.out.println(c4);
	}
}
