package com.zaumal.test.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.zaumal.test.mybatis.service.AppleService;
import com.zaumal.test.mybatis.utils.ApplicatoinContextUtil;

@MapperScan
@SpringBootApplication
public class MybatisApplication implements CommandLineRunner{
	public static void main(String[] args) {
		SpringApplication.run(MybatisApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("======begin=====");
		//测试配置sql的三种方式和
		//插入实体类数据，获取自增主键值
//		UserService userService = ApplicatoinContextUtil.getBean(UserService.class);
//		userService.test();
		
		//测试一对一、一对多
		AppleService appleService = ApplicatoinContextUtil.getBean(AppleService.class);
		appleService.test();
		
		System.out.println("=======end========");
		System.exit(0);
	}
	
	
}
