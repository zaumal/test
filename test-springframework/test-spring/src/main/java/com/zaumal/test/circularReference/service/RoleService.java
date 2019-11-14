package com.zaumal.test.circularReference.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
//@Lazy
public class RoleService {
	@Autowired
	private UserService userService;
	
//	@Autowired
//	public RoleService(UserService userService) {
//		System.out.println("RoleService init...");
//		this.userService = userService;
//	}
	
	public RoleService() {
		System.out.println("无参 RoleService init...");
	}
	
	public void t1() {
		System.out.println("RoleService t1 begin...");
		userService.t1();
		System.out.println("RoleService t1 end...");
	}
}
