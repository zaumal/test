package com.zaumal.test.mvc.initializer.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CustomFilter implements Filter {
	private int i = 0;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("CustomFilter init...");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("CustomFilter doFilter begin...");
		i++;
		chain.doFilter(request, response);
		System.out.println(i);
		System.out.println("CustomFilter doFilter end...");
	}

	@Override
	public void destroy() {
		System.out.println("CustomFilter destroy...");
	}

}
