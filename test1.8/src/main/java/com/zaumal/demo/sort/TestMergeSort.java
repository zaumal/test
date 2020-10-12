package com.zaumal.demo.sort;

/*
 * #归并排序法
 * 1、归并排序（MERGE-SORT）是利用归并的思想实现的排序方法，该算法采用经典的分治（divide-and-conquer）策略。
 * 2、分治法将问题分(divide)成一些小的问题然后递归求解
 * 3、治(conquer)的阶段则将分的阶段得到的各答案"修补"在一起，即分而治之
 */
public class TestMergeSort extends TestSort{
	@Override
	protected void sort(int[] data) {
		mergeSort(data);
	}
	
	/*
	 * 1、用递归的方法对数组不断进行分割
	 * 2、对分割后的两个数组中的元素按大小顺序合并
	 */
	public void mergeSort(int[] data) {
		sort(data,0,data.length-1);
	}
	
	private void sort(int[] data,int left,int right) {
		if(left < right) {
			int mid = (left+right)/2;
			sort(data,left,mid);
			sort(data,mid+1,right);
			merge(data,left,mid,right);
		}
	}
	
	private void merge(int[] data,int left,int mid,int right) {
		int[] temp = new int[right-left+1];
		
		int i = left; //左序列指针
		int j = mid+1; //右序列指针
		int k = 0; //临时数组指针
		
		//1、把两边较小的元素移动到临时数组中
		while(i <= mid && j <= right) {
			if(data[i] <= data[j]) {
				temp[k++] = data[i++];
			}else {
				temp[k++] = data[j++];
			}
		}
		
		//2、把左边剩余元素移动到临时数组中
		while(i <= mid) {
			temp[k++] = data[i++];
		}
		
		//3、把右边剩余元素移动到临时数组中
		while(j <= right) {
			temp[k++] = data[j++];
		}
		
		//4、把临时数组中的元素覆盖原数组  
		for(k = 0; k < temp.length; k++) {
			data[left++] = temp[k];
		}
	}
	
	public static void main(String[] args) {
		TestMergeSort t = new TestMergeSort();
		t.sort();
	}
}
