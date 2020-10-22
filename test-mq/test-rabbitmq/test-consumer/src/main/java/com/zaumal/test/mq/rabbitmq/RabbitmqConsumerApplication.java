package com.zaumal.test.mq.rabbitmq;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitmqConsumerApplication implements CommandLineRunner{
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(RabbitmqConsumerApplication.class);
		application.run(args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("消费者开始消费消息。。。");
		
	}
	
}
