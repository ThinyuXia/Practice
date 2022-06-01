package com.xiaxinyu.gallery.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.xiaxinyu.gallery.entity.Painting;
import com.xiaxinyu.gallery.service.PaintingService;
import com.xiaxinyu.gallery.utils.PageModel;

/**
 * 后台管理功能controller
 */
@WebServlet("/management")
public class ManagementController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PaintingService paintingService = new PaintingService();  

	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagementController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); //修改post请求体中的字符集
		response.setContentType("text/html;charset=utf-8");
		String method = request.getParameter("method");
		if(method.equals("list")) { //分页查询列表
			this.list(request,response);
		}else if(method.equals("show_create")) { //显示新增页面
			this.showCreatePage (request,response);
		}else if(method.equals("create")) { //新增油画
			this.create(request, response);
		}else if(method.equals("show_update")) { //新增油画
			this.showUpdate(request, response);
		}else if(method.equals("update")) {
			this.update(request,response);
		}else if(method.equals("delete")) {
			this.delete(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	String page = request.getParameter("p"); //页号
		String rows = request.getParameter("r"); //每页记录数
		if(page == null) page = "1";
		if(rows == null) rows = "6";
		
		PageModel pageModel = paintingService.pagination(Integer.parseInt(page), Integer.parseInt(rows));
		request.setAttribute("pageModel", pageModel);
		request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request,response);
    } 
    
    private void showCreatePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	request.getRequestDispatcher("/WEB-INF/jsp/create.jsp").forward(request, response);
    }
    
    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	/**
    	 * 文件上传时的数据处理和标准表单完全不同
    	 * String pname = request.getParameter("");
    	 * System.out.println(pname);
    	 */
    	
    	//1.初始化FileUpLoad组件
    	/**
    	 * FileItemFactory用于将前端表单数据转换为一个个FileItem对象
    	 * ServletFileUpload为FileUpLoad组件提供了JavaWeb的Http请求解析
    	 */
    	FileItemFactory  factory = new DiskFileItemFactory(); 
    	ServletFileUpload sf = new ServletFileUpload(factory);
    	//2.遍历所有FileItem
    	try {
			List<FileItem> formData = sf.parseRequest(request);
			Painting p = new Painting();
			for(FileItem fi : formData) {
				if(fi.isFormField()) { //判断当前的对象是普通表单输入项还是文件上传框
					System.out.println(fi.getString("utf-8"));
					switch(fi.getFieldName()){
						case "pname":
							p.setPname(fi.getString("utf-8"));
							break;
						case "category":
							p.setCategory(Integer.parseInt(fi.getString("utf-8")));
							break;
						case "price":
							p.setPrice(Integer.parseInt(fi.getString("utf-8")));
							break;
						case "description":
							p.setDescription(fi.getString("utf-8" ));
							break; 
						default:
							break;
					}
				}else {
			    	//3.将文件保存到服务器目录
					String path = request.getServletContext().getRealPath("/upload"); //用于获取tomcat在实际运行环境下的指定目录的物理地址
					System.out.println("文件上传目录 : " + path);
					String fileName = UUID.randomUUID().toString(); //生成随机字符串,长度为36
					// fi.getName()方法得到原始文件名，将文件名中最后一个.以及后面的所有字符串进行截取并添加到文件名上作为文件扩展名
					String suffix = fi.getName().substring(fi.getName().lastIndexOf("."));
					// fi.write() 写入目标文件
					fi.write(new File(path,fileName + suffix));
					p.setPreview("/upload/" + fileName + suffix);
				}
				
			}
			paintingService.create(p); //新增功能
			//如果新增后存在后续操作则使用请求转发，否则使用请求重定向
			response.sendRedirect("/management?method=list"); 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    //显示更新页面
    private void showUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String id = request.getParameter("id");
    	Painting p = paintingService.findById(Integer.parseInt(id));
    	request.setAttribute("painting", p);
//    	System.out.println(id); 
    	request.getRequestDispatcher("/WEB-INF/jsp/update.jsp").forward(request, response);
    }
    
    private void update(HttpServletRequest request, HttpServletResponse response) {
    	FileItemFactory factory = new DiskFileItemFactory();
    	ServletFileUpload sf = new ServletFileUpload(factory);
    	try {
			List<FileItem> formData = sf.parseRequest(request);
			boolean isPreviewModified = false;
			Painting p = new Painting();
			for(FileItem fi : formData) {
				if(fi.isFormField()) { //判断当前的对象是普通表单输入项还是文件上传框
//					System.out.println(fi.getString("utf-8"));
//					System.out.println(fi.getFieldName());
					switch(fi.getFieldName()){
						case "pname":
							p.setPname(fi.getString("utf-8"));
							break;
						case "category":
							p.setCategory(Integer.parseInt(fi.getString("utf-8")));
							break;
						case "price":
							p.setPrice(Integer.parseInt(fi.getString("utf-8")));
							break;
						case "description":
							p.setDescription(fi.getString("utf-8" ));
							break; 
						case "isPreviewModified":
							if(Integer.parseInt(fi.getString()) == 1)
							isPreviewModified = true;
							break;
						case "id":
							p.setId(Integer.parseInt(fi.getString()));
							break;
						default:
							break;
					}	
			}else if(isPreviewModified){
				String path = request.getServletContext().getRealPath("/upload");
				String fileName = UUID.randomUUID().toString();
				String suffix = fi.getName().substring(fi.getName().lastIndexOf("."));
				fi.write(new File(path,fileName + suffix));
				p.setPreview("/upload/" + fileName + suffix);
				}
			}
			paintingService.update(p,isPreviewModified);
			response.sendRedirect("/management?method=list");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    /**
	 * 客户端采用Ajax方式提交请求
	 * Controller方法处理完后不再跳转任何jsp页面，而是通过响应输出JSON格式字符串
	 * Tips：作为Ajax请求与服务器交互后，得到的不是整页html，而是服务器处理后的数据
	 */
    public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	Integer id = Integer.parseInt(request.getParameter("id"));
    	try {
    		paintingService.delete(id);
    		response.getWriter().println("{\"result\":\"ok\"}");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.getWriter().println("{\"result\":\"" + e.getMessage() + "\"}");
		}
    }
}
