package com.zaumal.test.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.DelayQueue;

public class TestQueue {
	public static void main(String[] args) {
		ArrayBlockingQueue<Integer> abq = new ArrayBlockingQueue<>(2);
		
//		System.out.println(abq.add(1));
//		System.out.println(abq.add(2));
		System.out.println(abq.remove(2));
		System.out.println(abq.offer(1));
		DelayQueue dq = new DelayQueue();
	}
}
