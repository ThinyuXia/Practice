 package com.xiaxinyu.gallery.dao;

import java.util.ArrayList;
import java.util.List;

import com.xiaxinyu.gallery.entity.Painting;
import com.xiaxinyu.gallery.utils.PageModel;
import com.xiaxinyu.gallery.utils.XmlDataSource;

/**
 * 
 *油画数据访问对象
 */
public class PaintingDao {
	/**
	 * 分页查询油画数据
	 * @param page 查询页数
	 * @param rows 每页记录数
	 * @return PageModel 分页对象
	 */
	public PageModel pagination(int page,int rows) {
		List<Painting > data = XmlDataSource.getRawData();
		return new PageModel(data,page,rows);
	} 
	
	/**
	 * 按类别分类查询
	 * @param category 类别编号
	 * @param page 页号
	 * @param rows 每页数据
	 * @return 分页对象
	 */
	public PageModel pagination(int category,int page,int rows) {
		List<Painting > list = XmlDataSource.getRawData();
		List<Painting> categoryList = new ArrayList<>();
		for(Painting p : list) {
			if(p.getCatagory() == category) 
				categoryList.add(p);
		}
		return new PageModel(categoryList,page,rows);
	}
 
}
