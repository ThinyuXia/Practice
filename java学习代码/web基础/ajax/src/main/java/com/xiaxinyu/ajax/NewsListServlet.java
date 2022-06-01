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
 * Servlet implementation class NewsListServlet
 */
@WebServlet("/news")
public class NewsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("t");
		List<News> list = new ArrayList<>();
		if(type != null && type.equals("pypl")) {
			list.add(new News("PYPL:2018年6月份全球编程语言排行榜","2018-5-1","PYPL","..."));
			list.add(new News("PYPL:2018年7月份全球编程语言排行榜","2018-5-1","PYPL","..."));
			list.add(new News("PYPL:2018年8月份全球编程语言排行榜","2018-5-1","PYPL","..."));
			list.add(new News("PYPL:2018年9月份全球编程语言排行榜","2018-5-1","PYPL","..."));
		}else if(type == null || type.equals("tiobe")){
			list.add(new News("TIOBE:2018年6月份全球编程语言排行榜","2018-5-1","TIOBE","..."));
			list.add(new News("TIOBE:2018年7月份全球编程语言排行榜","2018-5-1","TIOBE","..."));
			list.add(new News("TIOBE:2018年8月份全球编程语言排行榜","2018-5-1","TIOBE","..."));
			list.add(new News("TIOBE:2018年9月份全球编程语言排行榜","2018-5-1","TIOBE","..."));
		}
		String json = JSON.toJSONString(list);
		System.out.println(json);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().println(json);
	}

}
