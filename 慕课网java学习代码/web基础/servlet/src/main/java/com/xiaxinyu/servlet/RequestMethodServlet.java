package com.xiaxinyu.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestMethodServlet extends HttpServlet{
	
	// 处理Get请求
	public void doGet(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		String name = req.getParameter("name"); 
		resp.getWriter().println("Get : " + name);
	}
	
	// 处理Post请求
	public void doPost(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		String name = req.getParameter("name"); 
		resp.getWriter().println("Post : " + name);
	}
}
