package com.zaumal.demo.sort;

/*
 * #冒泡排序法
 * 1、相邻两个元素进行比较，根据比较结果进行交换（比如将小值放后面），最终找出最小的值，并且放在最后一个位置
 * 2、重复步骤1（比较n-1次，以此类推，每次少1位）
 * (选出每一次剩余序列中的最大（小）值放到最后（前）一位，直到序列中无数据，形似冒泡)
 */
public class TestBubbleSort extends TestSort{
	public void bubbleSort(int[] data) {
		for(int i = 1; i < data.length; i++) {
			for(int j = 0; j < data.length-i; j++) {
				if(data[j] < data[j+1] ) {
					int tmp = data[j+1];
					data[j+1] = data[j];
					data[j] = tmp;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		TestBubbleSort t = new TestBubbleSort();
		t.sort();
	}

	@Override
	protected void sort(int[] data) {
		bubbleSort(data);
	} 
}
