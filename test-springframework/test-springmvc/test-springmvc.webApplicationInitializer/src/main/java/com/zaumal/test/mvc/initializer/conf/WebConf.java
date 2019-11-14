package com.zaumal.test.mvc.initializer.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan("com.zaumal.test.mvc.initializer.controller")
@EnableWebMvc
public class WebConf {

}
