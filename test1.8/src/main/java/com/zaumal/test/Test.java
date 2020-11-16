package com.zaumal.test;

import java.util.ArrayList;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		t1();
	}
	
	public static void t1() {
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < 50; i++){
			list.add(i);
		}
		
		list.parallelStream().forEach(System.out::println);
//		System.out.println("============================");
//		list.stream().forEach(System.out::println);
	}
}
