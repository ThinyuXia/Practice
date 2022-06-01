package com.xiaxinyu.freemarker.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListServlet
 */
@WebServlet("/list")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List list = new ArrayList();

		list.add(new Employee(3645,"zhangsan","市场部","客户代表",10000d));
		list.add(new Employee(3644,"xiaxinyu","研发部","高级研发工程师",40000d));
		request.setAttribute("list", list);
		request.getRequestDispatcher("/employee.ftl").forward(request, response);
		
		
	}

}
