package com.xiaxinyu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName="CharacterEncodingFilter",urlPatterns="/*",initParams={
		@WebInitParam(name="encoding1",value="utf-8"),
		@WebInitParam(name="encoding2",value="gbk")
}) //无论采用配置形式还是注解形式，filter-name必须是唯一的
public class CharacterEncodingFilter implements Filter{
	private String encoding;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
			HttpServletRequest req = (HttpServletRequest) request;
			req.setCharacterEncoding("utf-8");
			HttpServletResponse resp = (HttpServletResponse) response;
			resp.setContentType("text/html;charset=utf-8");
			chain.doFilter(request, response);
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		encoding = filterConfig.getInitParameter("encoding2");
		System.out.println("encoding2: " + encoding);
	}

}
