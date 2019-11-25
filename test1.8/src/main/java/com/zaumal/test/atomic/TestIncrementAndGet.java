package com.zaumal.test.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class TestIncrementAndGet {
	public static void main(String[] args) {
		AtomicInteger i = new AtomicInteger(1);
		System.out.println(i.incrementAndGet());
		System.out.println(i.incrementAndGet());
		System.out.println(i.incrementAndGet());
		System.out.println(i.incrementAndGet());
		System.out.println(i.incrementAndGet());
	}
}
