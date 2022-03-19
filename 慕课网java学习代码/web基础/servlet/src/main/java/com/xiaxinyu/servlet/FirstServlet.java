package com.xiaxinyu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//接收请求中的参数
		String name = req.getParameter("name");
		String html = "<h1 style='color : black'>"+ "Hi " + name + " !</h1><hr>";
		PrintWriter out = resp.getWriter();
		//将html发送回浏览器
		out.println(html);
	}
	

}
