package com.xiaxinyu.servlet.direct;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalSum
 */
@WebServlet("/direct/sum")
public class CalSum extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalSum() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer num =  Integer.parseInt(request.getParameter("number"));
		int ans = 0;
		for(int i = 0;i <= num;i ++) ans += i;
		request.setAttribute("num", ans);
		request.getRequestDispatcher("/direct/result").forward(request, response);
	}

}
