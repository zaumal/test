package com.zaumal.test.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class TestAtomicIntegeFieldUpdater {
	public static void main(String[] args) {
		User user = new User();
		AtomicIntegerFieldUpdater<User> a = AtomicIntegerFieldUpdater.newUpdater(User.class, "age");
		a.set(user, 12);
		System.out.println(a.get(user));
		System.out.println(user.getAge());
	}
}
class User{
	private String name;
	public volatile int age;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
