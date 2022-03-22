package com.xiaxinyu.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SelectFruit
 */
@WebServlet("/select/fruit")
public class SelectFruit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectFruit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HashMap<String,String> map = new HashMap<>();
		map.put("apple", "苹果");
		map.put("banana", "香蕉");
		map.put("orange", "橘子");
		String key = (String) request.getParameter("key");

		if(map.containsKey(key)) {
			request.getSession().setAttribute("value", map.get(key));
			request.getRequestDispatcher("/success.jsp").forward(request, response);;
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("result", "没有找到对应的结果");
			response.sendRedirect("/servlet/fail.jsp");
		}
	}

}
