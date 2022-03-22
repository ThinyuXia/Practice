package com.xiaxinyu.servlet.charset;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CharsetServlet
 */
@WebServlet("/charset/process")
public class CharsetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CharsetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	// 对于Tomcat8.x以上的版本，默认get请求发送中文就是utf-8的格式
		req.setCharacterEncoding("utf-8"); 
		String name = req.getParameter("ename");
		String address = req.getParameter("eaddress");
		System.out.println(name  + " : " + address);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//req.setCharacterEncoding("utf-8"); 用于将-请求体-中的字符集转换成utf-8
		// 注意该方法只对post请求有效
		req.setCharacterEncoding("utf-8"); 
		String name = req.getParameter("ename");
		String address = req.getParameter("eaddress");
//		String utf8Name = new String(name.getBytes("iso-8859-1"),"utf-8");
//		String utf8Address = new String(address.getBytes("iso-8859-1"),"utf-8");
		System.out.println(name  + " : " + address);
		
	}

}
