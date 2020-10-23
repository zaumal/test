package com.zaumal.test.mybatis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zaumal.test.mybatis.entity.AppleExt;
import com.zaumal.test.mybatis.mapper.AppleMapper;

@Service
public class AppleService {
	@Autowired
	private AppleMapper appleMapper;
	
	public AppleExt xmlQueryById(Integer id) {
		return appleMapper.xmlQueryById(id);
	}
	
	public void test() {
		System.out.println("===========xml一对一、一对多=============");
		System.out.println(xmlQueryById(102)); //belong:202,eaters:202、203、204
	}
}
