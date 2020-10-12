package com.zaumal.demo.sort;

/*
 * #插入排序
 * 1、假定一个参考值，假设该参考值左边的元素都有序，那么从该元素开始从后往前挨个查找，
 * 2、如果找到比参考值大的数，那么就将这个大的数后移，
 * 3、如果未找到比参考值大的数，说明不用移动元素。
 * 4、循环比较，这样经过比较后移之后就会空出下标为0的位置，用于存放这个参考值。
 */
public class TestInsertionSort extends TestSort {
	@Override
	protected void sort(int[] data) {
		insertionSort(data);
	}
	
	public void insertionSort(int[] data) {
		//1、假设第一个元素是一个有序序列，从第二个元素开始比较
		for(int i = 1; i < data.length; i++) {
			int tmp = data[i];
			
			//2、当前元素和前面的有序序列的每一个元素比较，如果有序序列中的元素大，就往后移一位
			int j = i-1;
			for(; j >=0 ; j--) {
				if(tmp < data[j]) {
					data[j+1] = data[j];
				}else {
					//3、找不到比当前元素更大的元素，就跳出循环
					break;
				}
			}
			
			//4、将当前元素放在前面的有序序列中空出来的位置上
			data[j+1] = tmp;
		}
	}

	public static void main(String[] args) {
		TestInsertionSort t = new TestInsertionSort();
		t.sort();
	}
}
