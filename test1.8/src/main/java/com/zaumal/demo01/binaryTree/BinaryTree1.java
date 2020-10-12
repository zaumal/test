package com.zaumal.demo01.binaryTree;

import java.util.ArrayDeque;
import java.util.Queue;

/*
 * 普通二叉树
 */
public class BinaryTree1<T> {
	private T t;
	private BinaryTree1<T> lChild,rChild;
	
	public BinaryTree1(T t) {
		this.t = t;
	}
	
	public BinaryTree1(T t,BinaryTree1<T> lChild,BinaryTree1<T> rChild) {
		this.t = t;
		this.lChild = lChild;
		this.rChild = rChild;
	}
	
	public void insertLeft(BinaryTree1<T> node) {
		if(this.lChild != null){
			node.insertLeft(this.lChild);
		}
		this.lChild = node;
	}
	
	public void insertRight(BinaryTree1<T> node) {
		if(this.rChild != null) {
			node.insertRight(this.rChild);
		}
		this.rChild = node;
	}
	
	public static void main(String[] args) {
		
		/*
		 * 						1
	     * 				2				3
		 * 			4		5		6		7
		 * 		   8 9    10 11   12 13   14 15
		 * 
		 */
		BinaryTree1<Integer> rr1 = new BinaryTree1<>(1);
		BinaryTree1<Integer> rr2 = new BinaryTree1<>(2);
		BinaryTree1<Integer> rr3 = new BinaryTree1<>(3);
		BinaryTree1<Integer> rr4 = new BinaryTree1<>(4);
		BinaryTree1<Integer> rr5 = new BinaryTree1<>(5);
		BinaryTree1<Integer> rr6 = new BinaryTree1<>(6);
		BinaryTree1<Integer> rr7 = new BinaryTree1<>(7);
		BinaryTree1<Integer> rr8 = new BinaryTree1<>(8);
		BinaryTree1<Integer> rr9 = new BinaryTree1<>(9);
		BinaryTree1<Integer> rr10 = new BinaryTree1<>(10);
		BinaryTree1<Integer> rr11 = new BinaryTree1<>(11);
		BinaryTree1<Integer> rr12 = new BinaryTree1<>(12);
		BinaryTree1<Integer> rr13 = new BinaryTree1<>(13);
		BinaryTree1<Integer> rr14 = new BinaryTree1<>(14);
		BinaryTree1<Integer> rr15 = new BinaryTree1<>(15);
		rr1.insertLeft(rr8);
		rr1.insertLeft(rr4);
		rr1.insertLeft(rr2);
		rr4.insertRight(rr9);
		rr2.insertRight(rr5);
		rr5.insertLeft(rr10);
		rr5.insertRight(rr11);
		rr1.insertRight(rr15);
		rr1.insertRight(rr7);
		rr1.insertRight(rr3);
		rr7.insertLeft(rr14);
		rr3.insertLeft(rr6);
		rr6.insertLeft(rr12);
		rr6.insertRight(rr13);
		
		
		BinaryTree1<Integer> r1 = new BinaryTree1<>(1);
		BinaryTree1<Integer> r2 = new BinaryTree1<>(2);
		r1.lChild = r2;
		BinaryTree1<Integer> r3 = new BinaryTree1<>(3);
		r1.rChild = r3;
		BinaryTree1<Integer> r4 = new BinaryTree1<>(4);
		r2.lChild = r4;
		BinaryTree1<Integer> r5 = new BinaryTree1<>(5);
		r2.rChild = r5;
		BinaryTree1<Integer> r6 = new BinaryTree1<>(6);
		r3.lChild = r6;
		BinaryTree1<Integer> r7 = new BinaryTree1<>(7);
		r3.rChild = r7;
		BinaryTree1<Integer> r8 = new BinaryTree1<>(8);
		r4.lChild = r8;
		BinaryTree1<Integer> r9 = new BinaryTree1<>(9);
		r4.rChild = r9;
		BinaryTree1<Integer> r10 = new BinaryTree1<>(10);
		r5.lChild = r10;
		BinaryTree1<Integer> r11 = new BinaryTree1<>(11);
		r5.rChild = r11;
		BinaryTree1<Integer> r12 = new BinaryTree1<>(12);
		r6.lChild = r12;
		BinaryTree1<Integer> r13 = new BinaryTree1<>(13);
		r6.rChild = r13;
		BinaryTree1<Integer> r14 = new BinaryTree1<>(14);
		r7.lChild = r14;
		BinaryTree1<Integer> r15 = new BinaryTree1<>(15);
		r7.rChild = r15;
		
		
		rr1.preOder();
		System.out.println();
		r1.preOder();
		System.out.println();
		
		rr1.midOrder();
		System.out.println();
		r1.midOrder();
		System.out.println();
		
		rr1.postOrder();
		System.out.println();
		r1.postOrder();
		System.out.println();
		
		rr1.bfsOrder();
		System.out.println();
		r1.bfsOrder();
		System.out.println();
		
	}
	
	//深度优先
	public void bfsOrder() {
		Queue<BinaryTree1<T>> queue = new ArrayDeque<>();
		queue.add(this);
		
		while(!queue.isEmpty()) {
			BinaryTree1<T> node = queue.poll();
			System.out.print(node.t.toString() + " ");
			
			if(null != node.lChild) {
				queue.add(node.lChild);
			}
			if(null != node.rChild) {
				queue.add(node.rChild);
			}
		}
	}
	
	//后序遍历
	public void postOrder() {
		if(null != this.lChild) {
			this.lChild.postOrder();
		}
		if(null != this.rChild) {
			this.rChild.postOrder();
		}
		System.out.print(this.t.toString() + " ");
	}
	
	//中序遍历
	public void midOrder() {
		if(null != this.lChild) {
			this.lChild.midOrder();
		}
		
		System.out.print(this.t.toString() + " ");
		
		if(null != this.rChild) {
			this.rChild.midOrder();
		}
	}
	
	//前序遍历
	public void preOder() {
		System.out.print(this.t.toString() + " ");
		
		if(null != this.lChild) {
			this.lChild.preOder();
		}
		
		if(null != this.rChild) {
			this.rChild.preOder();
		}
	}
	
	
	public T getT() {
		return t;
	}
	public void setT(T t) {
		this.t = t;
	}
	public BinaryTree1<T> getlChild() {
		return lChild;
	}
	public void setlChild(BinaryTree1<T> lChild) {
		this.lChild = lChild;
	}
	public BinaryTree1<T> getrChild() {
		return rChild;
	}
	public void setrChild(BinaryTree1<T> rChild) {
		this.rChild = rChild;
	}
}
