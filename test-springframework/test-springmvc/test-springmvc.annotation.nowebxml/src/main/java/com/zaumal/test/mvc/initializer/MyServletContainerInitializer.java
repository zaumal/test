package com.zaumal.test.mvc.initializer;

import java.util.EnumSet;
import java.util.Set;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class MyServletContainerInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
    	//设置rootWebApplicationContext
    	ctx.setInitParameter("contextClass", "org.springframework.web.context.support.AnnotationConfigWebApplicationContext");
    	//设置rootWebApplicationContext扫描的包
    	ctx.setInitParameter("contextConfigLocation", "com.zaumal.test.mvc.initializer.conf,com.zaumal.test.mvc.initializer.service");
    	
    	//注册ContextLoaderListener
        ctx.addListener(ContextLoaderListener.class);
        
        //注册DispatcherServlet
        javax.servlet.ServletRegistration.Dynamic dispatcherServlet = 
        		ctx.addServlet("springMVC", DispatcherServlet.class);
        //设置webApplicationContext
        dispatcherServlet.setInitParameter("contextClass", "org.springframework.web.context.support.AnnotationConfigWebApplicationContext");
        //设置webApplicationContext的扫描包
        dispatcherServlet.setInitParameter("contextConfigLocation", "com.zaumal.test.mvc.initializer.controller");
        dispatcherServlet.setLoadOnStartup(1);
        dispatcherServlet.setAsyncSupported(true);
        dispatcherServlet.addMapping("/");
        
        //注册Filter
        javax.servlet.FilterRegistration.Dynamic springEncodingFilter = 
        		ctx.addFilter("SpringEncodingFilter", CharacterEncodingFilter.class);
        springEncodingFilter.setInitParameter("encoding", "UTF-8");
        springEncodingFilter.setInitParameter("forceEncoding", "true");
        springEncodingFilter.setAsyncSupported(true);
        springEncodingFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/");
    }

}