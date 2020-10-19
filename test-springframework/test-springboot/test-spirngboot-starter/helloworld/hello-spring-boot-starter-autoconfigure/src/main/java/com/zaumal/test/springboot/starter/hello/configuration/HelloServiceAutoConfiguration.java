package com.zaumal.test.springboot.starter.hello.configuration;

import java.util.UUID;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaumal.test.springboot.starter.hello.properties.HelloProperties;
import com.zaumal.test.springboot.starter.hello.service.HelloService;

@Configuration
@EnableConfigurationProperties(HelloProperties.class)
public class HelloServiceAutoConfiguration {
	private HelloProperties helloProperties;
	
	public HelloServiceAutoConfiguration(HelloProperties helloProperties) {
		this.helloProperties = helloProperties;
	}

	@Bean
	@ConditionalOnMissingBean
	public HelloService helloService() {
		return new HelloService(UUID.randomUUID().toString(),helloProperties);
	}
}
