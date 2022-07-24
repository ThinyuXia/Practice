package com.xiaxinyu.mall.service;

import com.github.pagehelper.PageInfo;
import com.xiaxinyu.mall.model.pojo.Category;
import com.xiaxinyu.mall.model.request.AddCategoryReq;
import com.xiaxinyu.mall.vo.CategoryVO;

import java.util.List;

/**
 * 分类目录Service
 */
public interface CategoryService {
    void add(AddCategoryReq addCategoryReq);

    void update(Category updateCategory);

    void delete(Integer id);

    PageInfo listForAdmin(Integer pageNum, Integer pageSize);

    List<CategoryVO> listForCustomer();
}
