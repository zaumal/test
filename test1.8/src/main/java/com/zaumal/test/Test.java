package com.zaumal.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Test {
	public static void main(String[] args) throws Exception {
		int n = 20000;
		t1(n);
	}
	
	public static void t1(int n) throws Exception {
		long s1 = System.currentTimeMillis();
		StringJoiner sj = new StringJoiner(",");
		for(int i = 0; i < n; i++) {
			sj.add("12311112222");
		}
		long s2 = System.currentTimeMillis();
		
		File file = new File("t.txt");
		
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(sj.toString().getBytes());
		fos.flush();
		fos.close();
		
		
		System.out.println("StringBuilder : " + (s2-s1));
		
		System.out.println(sj.toString().getBytes().length/1024);
	}
}
