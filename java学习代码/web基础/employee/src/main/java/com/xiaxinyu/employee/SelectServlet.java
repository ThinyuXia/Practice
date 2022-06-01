package com.xiaxinyu.employee;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SelectServlet
 */
@WebServlet("/select")
public class SelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("selectByName");
		System.out.println(name);
		List emps = (List)request.getServletContext().getAttribute("employees");
		for(Object emp : emps) {
			Employee e = (Employee) emp;
			if(name.equals(e.getEname())){
				request.setAttribute("result", e);
				System.out.println("success");
				request.getRequestDispatcher("/select.jsp").forward(request, response);;
				break;
			}
		}
	}

}
