package com.zaumal.test.classLoad;

public class Test1 {
	public static void main(String[] args) {
		Child c = new Child("1");
		
	}
}

class Father{
	Father(String s){
		System.out.println("父类有参构造方法");
	}
	Father(){
		System.out.println("父类无参构造方法");
	}
}

class Child extends Father{
	Child(){
		System.out.println("子类无参构造方法");
	}
	Child(String s){
		System.out.println("子类有参构造方法");
	}
}
