package com.zaumal.demo;

import java.util.Random;

/*
 * #跳表
 */
public class SkipList {
	//链表结点类
    public class Node {
        public int data;
        //跳表结点的前后和上下都有指针
        public Node up, down, left, right;

        public Node(int data) {
            this.data = data;
        }
    }
    
    //结点“晋升”的概率
    private static final double PROMOTE_RATE = 0.5;
    //头尾节点
    private Node head,tail;
    //最大层级
    private int maxLevel;
    
    public SkipList() {
        head = new Node(Integer.MIN_VALUE);
        tail = new Node(Integer.MAX_VALUE);
        head.right = tail;
        tail.left = head;
    }
    
    //查找前置结点
    public Node findPreNode(int data){
        Node node = head;
        while(true){
        	while(node.right.data != Integer.MAX_VALUE && node.right.data <= data) {
        		node = node.right;
        	}
        	if(null == node.down) {
        		break;
        	}
        	node = node.down;
    	}
        return node;
    }
    //查找节点
    public boolean exist(int data) {
    	Node preNode = findPreNode(data);
    	if(preNode.data == data) {
    		return true;
    	}
    	return false;
    }
    
    //在前置结点后面添加新结点
    public void append(Node preNode,Node node) {
    	Node nextNode = preNode.right;
    	
    	preNode.right = node;
    	node.left = preNode;
    	
    	nextNode.left = node;
    	node.right = nextNode;
    }
    //增加一层
    public void addLevel() {
    	Node min = new Node(Integer.MIN_VALUE);
    	Node max = new Node(Integer.MAX_VALUE);
    	
    	min.right = max;
    	max.left = min;
    	
    	min.down = head;
    	max.down = tail;
    	
    	head.up = min;
    	tail.up = max;
    	
    	head = min;
    	tail = max;
    	
    	maxLevel++;
    }
    //插入节点
    public void insert(int data) {
    	Node preNode = findPreNode(data);
    	//如果节点已经存在，就返回
    	if(preNode.data == data) {
    		return;
    	}
    	//插入节点
    	Node node = new Node(data);
    	append(preNode, node);
    	//随机决定节点是否晋升
    	int currLevel = 0;
    	Random random = new Random();
    	while(random.nextDouble() < PROMOTE_RATE) {
    		//如果是最高层，就增加一层
    		if(currLevel == maxLevel) {
    			addLevel();
    		}
    		
    		//找到上一层的前置节点
    		while(null == preNode.up) {
    			preNode = preNode.left;
    		}
    		preNode = preNode.up;
    		
    		//把晋升的新节点插入上层
    		Node upNode = new Node(data);
    		append(preNode, upNode);
    		
    		upNode.down = node;
    		node.up = upNode;
    		
    		node = upNode;
    		currLevel++;
    	}
    }
    
    //删除一层
    public void removeLevel(Node leftNode) {
    	Node rightNode = leftNode.right;
    	if(null == leftNode.up) { //如果是最高层
    		leftNode.down.up = null;
    		rightNode.down.up = null;
    	}else {//如果不是最高层
    		leftNode.down.up = leftNode.up;
    		leftNode.up.down = leftNode.down;
    		
    		rightNode.down.up = rightNode.up;
    		rightNode.up.down = rightNode.down;
    	}
    	maxLevel--;
    }
    //删除节点
    public void remove(int data) {
    	Node preNode = findPreNode(data);
    	//无该节点
    	if(preNode.data != data) {
    		return;
    	}
    	
    	Node node = preNode;
    	int currLevel = 0;
    	while(null != node) {
    		node.left.right = node.right;
    		node.right.left = node.left;
    		
    		//如果不是最底层，且只有无穷小和无穷大结点，删除该层
            if(currLevel != 0 
            		&& node.left.data == Integer.MIN_VALUE 
            		&& node.right.data == Integer.MAX_VALUE){
                removeLevel(node.left);
            }else {
            	currLevel++;
            }
            node = node.up;
    	}
    }
    
    public void print() {
    	Node node = head;
    	while(null != node.down) {
    		node  = node.down;
    	}
    	while(node.right.data < Integer.MAX_VALUE) {
    		node = node.right;
			System.out.print(node.data + " ");
    	}
    	System.out.println();
    }
    
    public static void main(String[] args) {
    	SkipList sl = new SkipList();
    	sl.insert(5);
    	sl.print();
    	sl.insert(15);
    	sl.print();
    	sl.insert(2);
    	sl.print();
    	sl.insert(4);
    	sl.print();
    	sl.insert(50);
    	sl.print();
    	sl.insert(10);
    	sl.print();
    	sl.remove(4);
    	sl.print();
    	sl.remove(15);
    	sl.print();
    	sl.remove(50);
    	sl.print();
	}
}
