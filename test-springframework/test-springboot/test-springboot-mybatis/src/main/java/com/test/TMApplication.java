package com.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.test.entity.Te;
import com.test.service.TeService;

@MapperScan("com.test.mapper")
@SpringBootApplication
@EnableScheduling
public class TMApplication implements CommandLineRunner{
	@Autowired
	private TeService teService;
	
	public static void main(String[] args) {
		SpringApplication.run(TMApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("CommandLineRunner run...");
		Te selectObj = new Te();
		
		System.out.println("1 - " + teService.selectList(selectObj));
		
		Te te = new Te();
//		te.setId(12);
		te.setbId(3);
		System.out.println("id : " + te.getId());
		System.out.println("insert : " +teService.insert(te) + ", id : " + te.getId());
		
		System.out.println("4 - " + teService.selectList(selectObj));
	}

}
