package com.zaumal.demo01.binaryTree;

/*
 * 平衡二叉树
 */
public class BinaryTree3 {
	private Integer data;
	private BinaryTree3 lChild,rChild;
	
	
	
	
	
	public Integer getData() {
		return data;
	}
	public void setData(Integer data) {
		this.data = data;
	}
	public BinaryTree3 getlChild() {
		return lChild;
	}
	public void setlChild(BinaryTree3 lChild) {
		this.lChild = lChild;
	}
	public BinaryTree3 getrChild() {
		return rChild;
	}
	public void setrChild(BinaryTree3 rChild) {
		this.rChild = rChild;
	}
}
