package com.xiaxinyu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyFirstFilter implements Filter{

	private String encoding;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("过滤器销毁成功");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("过滤器已生效");
		HttpServletRequest req = (HttpServletRequest) request;
		req.setCharacterEncoding(encoding);
		HttpServletResponse resp = (HttpServletResponse) response;
		resp.setContentType("text/html;charset=" + encoding);
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("过滤器初始化成功");

		encoding = filterConfig.getInitParameter("encoding");
		System.out.println("encoding: " + encoding);
	}

	
}
