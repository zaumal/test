package com.zaumal.test.containerInitializer.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class TestFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("TestFilter init...");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("TestFilter doFilter begin..");
		chain.doFilter(request, response);
		System.out.println("TestFilter doFilter end..");
	}

	@Override
	public void destroy() {
		System.out.println("TestFilter destroy ...");
	}

}
