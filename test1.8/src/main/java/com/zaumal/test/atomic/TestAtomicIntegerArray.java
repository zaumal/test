package com.zaumal.test.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class TestAtomicIntegerArray {
	public static void main(String[] args) {
		AtomicIntegerArray aia = new AtomicIntegerArray(3);
		aia.addAndGet(1, 3);
		System.out.println(aia.get(1));
	}
}
