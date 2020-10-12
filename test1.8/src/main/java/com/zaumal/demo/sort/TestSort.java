package com.zaumal.demo.sort;

public abstract class TestSort {
	protected int[] data1 = {0,1,2,3,4,5,6,7,8,9};
	protected int[] data2 = {9,8,7,6,5,4,3,2,1,0};
	
	protected int[] data3 = {4,1,90,9,5,6,52,13,48,0,10,14,16};
	protected int[] data4 = {8,4,2,1,7,5,9,24,3,75,84,27,58,92,71,59,83,42,11,47,0,12,7,48,2};
	
	
	protected void println() {
		System.out.println("===============================================");
	}
	protected void print(int[] data) {
		for(int i = 0; i < data.length; i++) {
			System.out.print(data[i] + " ");
		}
		System.out.println();
	}
	
	protected abstract void sort(int[] data);
	
	protected void sort() {
		print(data1);
		sort(data1);
		print(data1);
		println();
		
		print(data2);
		sort(data2);
		print(data2);
		println();
		
		print(data3);
		sort(data3);
		print(data3);
		println();
		
		print(data4);
		sort(data4);
		print(data4);
		println();
	}
}
