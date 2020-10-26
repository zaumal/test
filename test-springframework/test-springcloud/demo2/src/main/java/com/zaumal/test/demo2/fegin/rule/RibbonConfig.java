package com.zaumal.test.demo2.fegin.rule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;
//import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;

@Configuration
public class RibbonConfig {
	@Bean
	public IRule myRule() {
//		return new RandomRule(); //随机
		return new RoundRobinRule();// 轮询，默认
	}
}
