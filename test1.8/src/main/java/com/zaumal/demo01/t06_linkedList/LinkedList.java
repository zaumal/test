package com.zaumal.demo01.t06_linkedList;

public class LinkedList {
	private Node header;
	private Node tail;
	private int length;
	
	public LinkedList() {
	}
	
	//增
	public boolean insert(int index,String o) {
		if(index > length) {
			return false;
		}
		Node node = new Node(o);
		if(index == length) {
			insert(o);
		}else if(index < length) {
			Node tmp1 = this.header;
			for(int i = 1; i < index; i++) {
				tmp1 = tmp1.next;			
			}
			Node tmp2 = tmp1.next;
			tmp1.next = node;
			node.next = tmp2;
		}
		length++;
		return true;
	}
	
	public boolean insert(String o) {
		Node node = new Node(o);
		
		if(null == header) {
			this.header = node;
			this.tail = node;
		} else {
			this.tail.next = node;
			this.tail = node;
		}
		length++;
		return true;
	}
	
	//删
	public boolean delete(int index) {
		if(null == this.header || index >= this.length) {
			return false;
		}
		
		Node tmp1 = this.header;
		for(int i = 1; i < index; i++) {
			if(null == tmp1.next) {
				break;
			}
			tmp1 = tmp1.next;
		}
		Node tmp2 = tmp1.next;
		
		tmp1.next = tmp2.next;
		
		if(null == tmp1.next) {
			this.tail = tmp1;
		}
		
		length--;
		return true;
	}
	
	//查
	public String get(int index) {
		if(null == this.header || index >= this.length) {
			return null;
		}
		
		Node node = this.header;
		for(int i = 1; i <= index; i++) {
			if(null == node.next) {
				break;
			}
			node = node.next;			
		}
		return node.data;
	}
	
	
	public static void main(String[] args) {
		LinkedList ll = new LinkedList();
		
		ll.insert("1");
		ll.insert("2");
		ll.insert("3");
		ll.insert("4");
		
		ll.insert(1,"12");
		
		for(int i = 0; i < ll.length; i++) {
			System.out.println(ll.get(i));
		}
		
	}
}

class Node{
	public String data;
	public Node next;
	
	public Node(String data) {
		this.data = data;
	}
}
