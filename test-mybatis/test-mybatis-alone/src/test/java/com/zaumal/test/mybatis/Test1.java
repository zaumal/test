package com.zaumal.test.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.zaumal.test.mybatis.entity.User;

public class Test1 {
	private SqlSession sqlSession;
	
	@Before
	public void test1() throws IOException {
		// 配置mybatis获得输入流
        InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
        // 创建 SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //从 SqlSessionFactory 中获取 SqlSession
        sqlSession = sqlSessionFactory.openSession();
	}
	
	@Test
	public void test2() {
		List<User> users = sqlSession.selectList("user.xmlQueryAll");
		users.forEach(System.out::println);
	}
}
