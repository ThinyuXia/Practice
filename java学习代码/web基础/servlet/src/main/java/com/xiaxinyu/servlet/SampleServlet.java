package com.xiaxinyu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SampleServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		  PrintWriter out =   resp.getWriter(); //向浏览器输出的数据流
		  out.println("<a href='https://www.baidu.com'>百度</a>");
		  
	}
}
