package com.zaumal.test.mybatis.sql;

import org.apache.ibatis.annotations.Param;

import com.zaumal.test.mybatis.entity.User;

public class UserSql {
	public String queryAgeByName(@Param("name") String name) {
		return "select age from test_mybatis_user where name=#{name}";
	}
	
	public String providerInsert(User user) {
		return "insert into test_mybatis_user(name,age) value(#{name},#{age})";
	}
}
