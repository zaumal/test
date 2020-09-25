package com.zaumal.demo01;

public class SearchDemo {
	public static void main(String[] args) {
		SearchDemo demo = new SearchDemo();
		//二分查找法
		int[] arr1 = {1,3,4,6,7,8,9,11,13,14,15,16,176,190,200};
		System.out.println(demo.binarySearch1(arr1, 9) + " : " + demo.binarySearch2(arr1, 9,0,arr1.length-1));
		System.out.println(demo.binarySearch1(arr1, 190) + " : " + demo.binarySearch2(arr1, 190,0,arr1.length-1));
		System.out.println(demo.binarySearch1(arr1, 10) + " : " + demo.binarySearch2(arr1, 10,0,arr1.length-1));
	}
	
	
	//二分查找法：一种在有序数组中查找特定元素的搜索算法
	//非递归
	public int binarySearch1(int[] arr,int key) {
		int startIndex = 0;
		int endIndex = arr.length - 1;
		while(startIndex <= endIndex) {
			int midIndex = (startIndex + endIndex) / 2;
			int midKey = arr[midIndex];
			if(midKey == key) {
				return midIndex;
			}else if(midKey < key) {
				startIndex = midIndex + 1;
			}else{
				endIndex = midIndex - 1;
			}
		}
		return -1;
	}
	
	//二分查找法
	//递归
	public int binarySearch2(int[] arr,int key,int startIndex,int endIndex) {
		if(startIndex > endIndex) {
			return -1;
		}
		
		int midIndex = (startIndex + endIndex) / 2;
		int midKey = arr[midIndex];
		if(midKey == key) {
			return midIndex;
		}else if(midKey < key) {
			startIndex = midIndex + 1;
			return binarySearch2(arr, key, startIndex, endIndex);
		}else{
			endIndex = midIndex - 1;
			return binarySearch2(arr, key, startIndex, endIndex);
		}
	}
}
