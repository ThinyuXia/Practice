package com.xiaxinyu.servlet.cookie;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.Cookie;
/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/cookies/index")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cs = request.getCookies();
		String user = null;
		if(cs == null) {
			response.getWriter().println("user not login");
		for(Cookie c : cs) {
			System.out.println(c.getName() + " : " + c.getValue());
			if(c.getName().equals("user"))
				{
					user = c.getValue();
					break;
				}
		}
		if(user == null) {
			response.getWriter().println("user not login");
		}else {
			response.getWriter().println(user + " login");
		}	
	}
	}	
}
