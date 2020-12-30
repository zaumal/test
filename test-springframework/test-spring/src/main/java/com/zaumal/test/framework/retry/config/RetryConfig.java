package com.zaumal.test.framework.retry.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;

@Configuration
@EnableRetry
@ComponentScan("com.zaumal.test.framework.retry")
public class RetryConfig {

}
