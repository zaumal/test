package com.zaumal.security.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan("com.zaumal.security.controller")
@EnableWebMvc
public class WebConf {

}
