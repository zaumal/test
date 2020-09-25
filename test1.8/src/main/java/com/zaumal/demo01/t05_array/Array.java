package com.zaumal.demo01.t05_array;

/*
 * 1）实现一个支持动态扩容的数组
 * 2）支持增删改查
 * 3）动态扩容2倍
 */
public class Array {
	//存放数据的数组
	private int[] data;
	//数组长度
	private int length;
	//实际存放数据个数
	private int count;
	
	public Array(int capacity) {
		this.data = new int[capacity];
		this.length = capacity;
	}
	
	//增
	public boolean insert(int index,int o) {
		if(count == length) {
			int[] newData = new int[length*2];
			for(int i = 0; i < length; i++) {
				newData[i] = data[i];
			}
			data = newData;
			length = length*2;
		}
		
		if(index >= count || index < 0) {
			return false;
		}
		
		for(int i = count; i > index;) {
			data[i] = data[--i];
		}
		
		data[index] = o;
		
		return true;
	}
	public boolean insert(int o) {
		if(count == length) {
			int[] newData = new int[length*2];
			for(int i = 0; i < length; i++) {
				newData[i] = data[i];
			}
			data = newData;
			length = length*2;
		}
		
		data[count++] = o;
		
		return true;
	}
	
	//删,根据索引删除
	public boolean delete(int index) {
		if(index >= count || index < 0) {
			return false;
		}
		for(int i = index; i < count;) {
			data[i] = data[++i];
		}
		count--;
		return true;
	}
	
	//改
	public boolean modify(int index,int o) {
		if(index >= count || index < 0) {
			return false;
		}
		data[index] = o;
		return true;
	}
	
	//查
	public int find(int index) {
		if(index >= count || index < 0) {
			return -1;
		}
		return data[index];
	}
	
	public void print() {
		for(int i = 0; i < count; i++) {
			System.out.print(data[i] + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		Array a1 = new Array(3);
		a1.insert(1);
		a1.print();
		a1.insert(2);
		a1.print();
		a1.insert(3);
		a1.print();
		a1.insert(4);
		a1.print();
		
		a1.delete(3);
		a1.print();
		
		a1.insert(5);
		a1.print();
		a1.insert(6);
		a1.print();
		a1.insert(7);
		a1.print();
		
		a1.insert(3,4);
		a1.print();
		
		a1.insert(8);
		a1.print();
		a1.insert(9);
		a1.print();
		a1.insert(10);
		a1.print();
		a1.insert(11);
		a1.print();
		a1.insert(12);
		a1.print();
	}
	
}
