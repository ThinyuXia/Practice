package com.xiaxinyu.ajax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

/**
 * Servlet implementation class EmployeeListServlet
 */
@WebServlet("/employee")
public class EmployeeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Employee> emps = new ArrayList<>();
		emps.add(new Employee("小红","职员","人事部"));
		emps.add(new Employee("小明","经理","技术部"));
		emps.add(new Employee("小白","职员","无线事业部"));
		String json = JSON.toJSONString(emps);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().println(json);

	}


}
