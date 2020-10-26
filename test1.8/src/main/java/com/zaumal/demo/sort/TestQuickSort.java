package com.zaumal.demo.sort;

/*
 * #快速排序法
 * 1、快速排序的核心是分割，确定一个用作分割的基准数字，将原数组分割成两个部分，左边的比基准数字小，右边的比基准数字大。
 * 2、然后递归迭代，分别将左部和右部的两个部分进行快速排序，直到整个数组有序。
 * 参考：https://www.jianshu.com/p/305c83268510
 */
public class TestQuickSort extends TestSort{
	public void quickSort(int[] data) {
		quickSort(data,0,data.length-1);
	}
	
	private void quickSort(int[] data,int low,int high) {
		if(low < high) {
			int index = partition(data,low,high);
			quickSort(data,low,index-1);
			quickSort(data,index+1,high);
		}
	}
	
	/*
	 * low：左哨兵，high：右哨兵
	 */
	private int partition(int[] data,int low,int high) {
		//1、随便找一个基准元素
		int n = data[low];
		while(low < high) {
			//2、先从右往左找到第一个比基准元素小的元素
			while(low < high && data[high] >= n) {
				high--;
			}
			//3、将找到的元素放到左哨兵的位置
			data[low] = data[high];
			
			//4、再从左往右找到第一个比基准元素大的元素
			while(low < high && data[low] <= n) {
				low++;
			}
			//5、将找到的元素放到右哨兵的位置
			data[high] = data[low];
			
			//6、循环一直到low和high相遇
		}
		
		//7、将基准元素放到左哨兵的位置（此时，左哨兵和右哨兵的位置重合）
//		System.out.println(low==high);
		data[low] = n;
		
		//low为基准元素所在的位置
		return low;
	}
	
	public static void main(String[] args) {
		TestQuickSort t = new TestQuickSort();
		
		t.sort();
	}

	@Override
	protected void sort(int[] data) {
		quickSort(data);
	}
	
}
