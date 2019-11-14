package com.zaumal.test.containerInitializer;

import java.util.EnumSet;
import java.util.Set;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import com.zaumal.test.containerInitializer.filter.TestFilter;
import com.zaumal.test.containerInitializer.listener.StartupListener;
import com.zaumal.test.containerInitializer.servlet.TestServlet;

public class MyServletContainerInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        //注册Servlet
        javax.servlet.ServletRegistration.Dynamic servletRegistration = ctx.addServlet("test", TestServlet.class);
        servletRegistration.addMapping("/servlet3/test");
        servletRegistration.setLoadOnStartup(1);
        servletRegistration.setAsyncSupported(true);
        
        //注册监听器
        ctx.addListener(StartupListener.class);
        
        //注册Filter
        javax.servlet.FilterRegistration.Dynamic filterRegistration = ctx.addFilter("test", TestFilter.class);
        filterRegistration.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/servlet3/*");
    }

}