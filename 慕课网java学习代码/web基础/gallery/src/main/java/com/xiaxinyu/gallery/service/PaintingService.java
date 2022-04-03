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
	
	/**
	 * 新增油画
	 * @param p 新增的油画对象
	 */
	public void create(Painting p) {
		paintingDao.create(p);
	}
	
	/**
	 * 按编号查询油画
	 * @param id 油画id
	 * @return 油画对象
	 */
	public Painting findById(Integer id) {
		Painting p = paintingDao.findById(id);
		if(p == null)
			throw new RuntimeException("[id = " + id + "]" + "油画不存在");
		return p;
	}
	
	public void update(Painting newPainting,boolean isPreviewModified) {


		Painting oldPainting = findById(newPainting.getId());
		if(isPreviewModified) {
			oldPainting.setPreview(newPainting.getPreview());
		}
		oldPainting.setCategory(newPainting.getCategory());
		oldPainting.setDescription(newPainting.getDescription());
		oldPainting.setPname(newPainting.getPname());
		oldPainting.setPrice(newPainting.getPrice());
		paintingDao.update(oldPainting);
	}
	
	public void delete(Integer id) {
		paintingDao.delete(id);
	}
}
