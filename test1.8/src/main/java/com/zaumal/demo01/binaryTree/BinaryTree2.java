package com.zaumal.demo01.binaryTree;

import java.util.ArrayDeque;
import java.util.Queue;

/*
 * 二叉搜索树
 */
public class BinaryTree2 {
	private Integer data;
	private BinaryTree2 lChild,rChild;
	
	public BinaryTree2(Integer data) {
		this.data = data;
	}

	public static void main(String[] args) {
		/*
		 * 				      61
		 * 			 59                87
		 *      47        60       73        98
		 * 35       51                    93
		 *     37
		 * 
		 */
		
		BinaryTree2 t = new BinaryTree2(61);
		t.insertNode(87);
		t.insertNode(59);
		t.insertNode(47);
		t.insertNode(35);
		t.insertNode(73);
		t.insertNode(60);
		t.insertNode(51);
		t.insertNode(98);
		t.insertNode(37);
		t.insertNode(93);
		
		t.bfsOrder();
		System.out.println();
		System.out.println(t.findNode(60));
		System.out.println(t.findNode(37));
		System.out.println(t.findNode(47));
		
		t.removeNode(87);
		t.bfsOrder();
	}
	
	//删除
	public boolean removeNode(Integer data) {
		return this.removeNode(data,null);
	}
	//删除
	private boolean removeNode(Integer data,BinaryTree2 parent) {
		if(this.data < data && null != this.rChild) {
			return this.rChild.removeNode(data,this);
		}else if(this.data > data && null != this.lChild) {
			return this.lChild.removeNode(data,this);
		}else if(this.data == data){
			if(null == this.lChild && null == this.rChild && parent.lChild == this) { //无子节点，当前为父节点的左子节点
				parent.lChild = null;
			}else if(null == this.lChild && null == this.rChild && parent.rChild == this) { //无子节点，当前为父节点的右子节点
				parent.rChild = null;
			}else if(null != this.lChild && null == this.rChild && parent.lChild == this) { //有左子节点，当前为父节点的左子节点
				parent.lChild = this.lChild;
			}else if(null != this.lChild && null == this.rChild && parent.rChild == this) { //有左子节点，当前为父节点的右子节点
				parent.rChild = this.lChild;
			}else if(null == this.lChild && null != this.rChild && parent.lChild == this) { //有右子节点，当前为父节点的左子节点
				parent.lChild = this.rChild;
			}else if(null == this.lChild && null != this.rChild && parent.rChild == this) { //有右子节点，当前为父节点的右子节点
				parent.rChild = this.rChild;
			}else { //有左子节点和右子节点,先找到右子节点中的最小节点，把该节点移到当前节点位置
				BinaryTree2 rightMin = this.rChild.findMin();
				this.rChild.removeNode(rightMin.data,this);
				
				rightMin.lChild = this.lChild;
				rightMin.rChild = this.rChild;
				
				if(parent.lChild == this) {
					parent.lChild = rightMin;
				}else {
					parent.rChild = rightMin;
				}
			}
			return true;
		}else {
			return false;
		}
	}
	private BinaryTree2 findMin() {
		if(null == this.lChild) {
			return this;
		}else {
			return this.lChild.findMin();
		}
	}
	
	
	//查找
	public boolean findNode(Integer data) {
		if(this.data < data && null != this.rChild) {
			return this.rChild.findNode(data);
		}
		if(this.data > data && null != this.lChild) {
			return this.lChild.findNode(data);
		}
		return this.data == data;
	}
	
	//插入
	public void insertNode(Integer data) {
		if(this.data < data && null == this.rChild) { //为右子节点
			this.rChild = new BinaryTree2(data);
		}else if(this.data < data && null != this.rChild) { //为右子节点的子节点
			this.rChild.insertNode(data);
		}else if(this.data >= data && null == this.lChild) { //为左子节点
			this.lChild = new BinaryTree2(data);
		}else if(this.data >= data && null != this.lChild) { //为左子节点的子节点
			this.lChild.insertNode(data);
		}
	}
	
	//深度优先
	public void bfsOrder() {
		Queue<BinaryTree2> queue = new ArrayDeque<>();
		queue.add(this);
		
		while(!queue.isEmpty()) {
			BinaryTree2 node = queue.poll();
			System.out.print(node.data + " ");
			
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
		System.out.print(this.data + " ");
	}
	
	//中序遍历
	public void midOrder() {
		if(null != this.lChild) {
			this.lChild.midOrder();
		}
		
		System.out.print(this.data + " ");
		
		if(null != this.rChild) {
			this.rChild.midOrder();
		}
	}
	
	//前序遍历
	public void preOder() {
		System.out.print(this.data + " ");
		
		if(null != this.lChild) {
			this.lChild.preOder();
		}
		
		if(null != this.rChild) {
			this.rChild.preOder();
		}
	}

	public Integer getData() {
		return data;
	}

	public void setData(Integer data) {
		this.data = data;
	}

	public BinaryTree2 getlChild() {
		return lChild;
	}

	public void setlChild(BinaryTree2 lChild) {
		this.lChild = lChild;
	}

	public BinaryTree2 getrChild() {
		return rChild;
	}

	public void setrChild(BinaryTree2 rChild) {
		this.rChild = rChild;
	}
}
