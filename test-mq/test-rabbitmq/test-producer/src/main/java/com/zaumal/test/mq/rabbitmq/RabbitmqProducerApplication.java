package com.zaumal.test.mq.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.zaumal.test.mq.rabbitmq.service.SendMessageService;

@SpringBootApplication
public class RabbitmqProducerApplication implements CommandLineRunner{
	public static void main(String[] args) {
		SpringApplication applicaiton = new SpringApplication(RabbitmqProducerApplication.class);
		applicaiton.run(args);
	}
	
	@Autowired
	private SendMessageService sendMessageService;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("开始发送消息。。。");
		for(int i = 0; i < 100000; i++) {
			sendMessageService.sendDirectMessage(i);
			Thread.sleep(Math.round(15)*1000);
		}
		System.out.println("发送结束");
	}
}
