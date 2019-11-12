package com.zaumal.test;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Test {
	public static void main(String[] args) {
		HashMap<Integer,Integer> hm = new HashMap<>();
		hm.put(1, 1);
		hm.get(1);
		ConcurrentHashMap<String,String> chm = new ConcurrentHashMap<>();
		chm.put("1", "2");
		
		ThreadLocal<Integer> tl = new ThreadLocal<Integer>();
		tl.set(1);
	}
}
