package com.zaumal.test.mybatis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zaumal.test.mybatis.entity.User;
import com.zaumal.test.mybatis.mapper.UserMapper;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;
	
	public void annotationQueryAgeByName(String name) {
		System.out.println("annotation : " + userMapper.annotationQueryAgeByName(name));
	}
	
	public void xmlQueryAgeByName(String name) {
		System.out.println("xml : " + userMapper.xmlQueryAgeByName(name));
	}
	
	public void providerQueryAgeByName(String name) {
		System.out.println("provider : " + userMapper.providerQueryAgeByName(name));
	}
	
	public void delete(String name) {
		userMapper.delete(name);
	}
	
	public Integer annotationInsert(User user) {
		delete(user.getName());
		return userMapper.annotationInsert(user);
	}
	
	public Integer providerInsert(User user) {
		delete(user.getName());
		return userMapper.providerInsert(user);
	}
	
	
	public void test() {
		System.out.println("======三种配置sql方式=======");
		String name = "张三";
		annotationQueryAgeByName(name);
		xmlQueryAgeByName(name);
		providerQueryAgeByName(name);
		
		System.out.println("======注解插入实体类数据，获取自增主键值=======");
		User user = new User();
		user.setName("mahuateng");
		user.setAge(51);
		Integer insertCount = annotationInsert(user);
		System.out.println("插入 " + insertCount + " 条数据，自增主键：" + user.getId());
		annotationQueryAgeByName(user.getName());
		
		System.out.println("======provider注解插入实体类数据，获取自增主键值=======");
		User user2 = new User();
		user2.setName("mayun");
		user2.setAge(59);
		Integer insertCount2 = providerInsert(user2);
		System.out.println("插入 " + insertCount2 + " 条数据，自增主键：" + user2.getId());
		xmlQueryAgeByName(user2.getName());
		
		System.out.println("======xml插入实体类数据，获取自增主键值=======");
		User user3 = new User();
		user3.setName("liyanhong");
		user3.setAge(49);
		Integer insertCount3 = providerInsert(user3);
		System.out.println("插入 " + insertCount3 + " 条数据，自增主键：" + user3.getId());
		providerQueryAgeByName(user3.getName());
	}
}
