package com.xiaxinyu.gallery.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xiaxinyu.gallery.service.PaintingService;
import com.xiaxinyu.gallery.utils.PageModel;

/**
 * Servlet implementation class PaintingController
 */
@WebServlet("/page")
public class PaintingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PaintingService paintingService = new PaintingService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaintingController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.接收Http请求参数 
		String page = request.getParameter("p"); //页号
		String rows = request.getParameter("r"); //每页记录数
		String category = request.getParameter("c"); //种类编号
		if(page == null) page = "1";
		//2.调用Service方法得到请求结果
		if(rows == null) rows = "6";
		PageModel pageModel = paintingService.pagination(Integer.parseInt(page), Integer.parseInt(rows),category);
		request.setAttribute("pageModel", pageModel);
		//将请求转发至jsp(view)进行数据展现
		request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request,response);
	}

}
