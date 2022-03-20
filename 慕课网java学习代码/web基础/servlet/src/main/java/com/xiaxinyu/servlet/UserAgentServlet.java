package com.xiaxinyu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserAgentServlet
 */
@WebServlet("/ua")
public class UserAgentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAgentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userAgent = request.getHeader("User-Agent");
		response.setContentType("text/html;charset=utf-8");
		
		String output = "";
		if(userAgent.indexOf("Mac") != -1) {
			output = "<h1>" + "这是PC端首页" + "</h1>";
		}else if((userAgent.indexOf("iPhone") != -1) || (userAgent.indexOf("Android") != -1)){
			output = "<h1>" + "这是移动端首页" + "</h1>";
		}
		response.getWriter().println(output);
	}

	
}
