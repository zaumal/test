package com.zaumal.test.aop.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.zaumal.test.aop")
@EnableAspectJAutoProxy
public class AppConf {
	
}
