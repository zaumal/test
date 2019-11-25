package com.zaumal.test.atomic;

import java.util.concurrent.atomic.AtomicReference;

public class TestAtomicReference {
	public static void main(String[] args) {
		AtomicReference<String> air = new AtomicReference<>("Test1");
		System.out.println(air.getAndSet("tr"));
		System.out.println(air.getAndSet("tr"));
	}
}
