package com.zaumal.test.thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class TestForkJoin {
	public static void main(String[] args) {
		int len = 1000000;
		List<Integer> data = new ArrayList<>(len);
		for(int i = 0; i < len; i++) {
			data.add(ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE));
		}
		Num num = new Num(data);
		
		forkJoinThread(num);
		
		singleThread(data);
	}
	
	public static void forkJoinThread(Num num) {
		long t1 = System.currentTimeMillis();
		num.data.parallelStream().forEach(x -> {
			num.sum += x;
		});
		long t2 = System.currentTimeMillis();
		
		System.out.println("forkJoin -> sum : " + num.sum + "; time : " + (t2-t1));	
	}
	
	public static void singleThread(List<Integer> data) {
		long t1 = System.currentTimeMillis();
		int sum = 0;
		for(int i = 0; i < data.size(); i++) {
			int n = i*3*4*5*6/18/20;
			sum += n;
		}
		long t2 = System.currentTimeMillis();
		
		System.out.println("single -> sum : " + sum + "; time : " + (t2-t1));
	}
}
class Num{
	int sum;
	List<Integer> data;
	
	public Num(List<Integer> data) {
		this.data = data;
	}
}
