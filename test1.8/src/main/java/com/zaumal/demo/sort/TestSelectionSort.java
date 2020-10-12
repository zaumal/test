package com.zaumal.demo.sort;

public class TestSelectionSort extends TestSort {
	@Override
	protected void sort(int[] data) {
		selectionSort(data);
	}

	public void selectionSort(int[] data) {
		for(int i = 0; i < data.length; i++) {
			//1、找剩余序列中的最小元素的位置
			int minIndex = i;
			for(int j = i+1; j < data.length; j++) {
				if(data[minIndex] > data[j]) {
					minIndex = j;
				}
			}
			
			//2、将此轮找到的最小元素放到排好序的位置
			if(minIndex != i) {
				int tmp = data[minIndex];
				data[minIndex] = data[i];
				data[i] = tmp;
			}
			
			//循环
		}
	}
	
	public static void main(String[] args) {
		TestSelectionSort t = new TestSelectionSort();
		t.sort();
	}
}
