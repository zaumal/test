package com.zaumal.test.mq.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectRabbitConfig {
 
    //队列 
	//起名：testDirectQueue
    @Bean
    public Queue TestDirectQueue() {
        return new Queue("testDirectQueue",true);
    }
 
    //Direct交换机 
    //起名：testDirectExchange
    @Bean
    DirectExchange TestDirectExchange() {
        return new DirectExchange("testDirectExchange");
    }
 
    //绑定  
    //将队列和交换机绑定, 并设置用于匹配键：testDirectRouting
    @Bean
    Binding bindingDirect() {
        return BindingBuilder.bind(TestDirectQueue())
        		.to(TestDirectExchange())
        		.with("testDirectRouting");
    }
}