package com.zaumal.test.circularReference.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
//@Lazy
public class UserService {
	@Autowired
	private RoleService roleService;
	
//	@Autowired
//	public UserService(RoleService roleService) {
//		this.roleService = roleService;
//		System.out.println("UserService init...");
//	}
	
	public void init() {
		System.out.println("UserService init...");
	}
	
	public UserService() {
		System.out.println("无参 UserService init...");
	}
	
	public void t1() {
		System.out.println("UserService t1 begin...");
		roleService.t1();
		System.out.println("UserService t1 end...");
	}
}
