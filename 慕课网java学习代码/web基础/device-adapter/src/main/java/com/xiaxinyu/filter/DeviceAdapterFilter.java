package com.xiaxinyu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName="DeviceAdapterFilter",urlPatterns="*.html")
public class DeviceAdapterFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) arg0;
		HttpServletResponse resp = (HttpServletResponse) arg1;
		/*
		 * index.html 默认首页
		 * PC : desktop/index.html
		 * MOBILE : /mobile/index.html
		 * */
		String uri = req.getRequestURI();
		String url = req.getRequestURL().toString();
		System.out.println(uri);
		System.out.println(url);
		
		if(uri.startsWith("/desktop") || uri.startsWith("/mobile")) {
			chain.doFilter(req,resp);
		}else {
			 String userAgent = req.getHeader("user-agent").toLowerCase();
			 String target = "";
			 if(userAgent.indexOf("android") != -1 || userAgent.indexOf("iphone") != -1) {
				 String targetURI = "/mobile" + uri;
				 System.out.println("移动端设备正在访问，重新跳转URI ： " + targetURI);
				 resp.sendRedirect(targetURI);
			 }else {
				 String targetURI = "/desktop" + uri;
				 System.out.println("PC端设备正在访问，重新跳转URI ： " + targetURI);
				 resp.sendRedirect(targetURI);
			 }
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
}
