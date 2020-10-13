package com.zaumal.demo.search;

public class TestBinarySearch extends TestSearch{
	@Override
	protected int search(int n,int[] data) {
		return binarySearch(n,data);
	}
	
	public int binarySearch(int n,int[] data) {
		return binarySearch(n,data,0,data.length-1);
	}
	
	public int binarySearch(int n,int[] data,int low,int high) {
		if(low > high) {
			return -1;
		}else {
			int mid = (low+high)/2;
			if(n < data[mid]) {
				return binarySearch(n,data,0,mid-1);
			}else if(n > data[mid]) {
				return binarySearch(n,data,mid+1,high);
			}else {
				return mid;
			}
		}
	}
	
	public static void main(String[] args) {
		TestBinarySearch t = new TestBinarySearch();
		t.search(9);
	}
}
