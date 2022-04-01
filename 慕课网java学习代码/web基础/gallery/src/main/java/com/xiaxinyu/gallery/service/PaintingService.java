package com.xiaxinyu.gallery.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.xiaxinyu.gallery.dao.PaintingDao;
import com.xiaxinyu.gallery.entity.Painting;
import com.xiaxinyu.gallery.utils.PageModel;
import com.xiaxinyu.gallery.utils.XmlDataSource;

public class PaintingService {
	private PaintingDao paintingDao = new PaintingDao();
	
	/**
	 * 分页查询油画数据
	 * @param page 查询页数
	 * @param rows 每页记录数
	 * @return PageModel 分页对象
	 */
	public PageModel pagination(int page,int rows, String...category) {
		if(category.length == 0 || category[0] == null)
			return paintingDao.pagination(page,rows);
		return paintingDao.pagination(Integer.parseInt(category[0]), page, rows);
	}

	public static void main(String[] args) {
		 PaintingService paintingService = new PaintingService();
		 PageModel pageModel = paintingService.pagination(3, 6);
//		 System.out.println(pageModel.getPageData());
		 
	}
}
