package com.zaumal.demo.search;

public abstract class TestSearch {
	protected int[] data1 = {0,1,2,3,4,5,6,7,8,9};
//	protected int[] data2 = {9,8,7,6,5,4,3,2,1,0};
	protected int[] data3 = {2,5,7,9,12,14,20,26,30};
	
	private void print(int[] data) {
		for(int i = 0; i < data.length; i++) {
			System.out.print(data[i] + " ");
		}
	}
	
	protected abstract int search(int n,int[] data);
	
	protected void search(int n) {
		print(data1);
		System.out.println(": " + search(n,data1));
//		print(data2);
//		System.out.println(": " + search(n,data2));
		print(data3);
		System.out.println(": " + search(n,data3));
	}
}
