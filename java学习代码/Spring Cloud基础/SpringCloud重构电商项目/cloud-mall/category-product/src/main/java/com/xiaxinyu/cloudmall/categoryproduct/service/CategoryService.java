package com.xiaxinyu.cloudmall.categoryproduct.service;



import com.github.pagehelper.PageInfo;
import com.xiaxinyu.cloudmall.categoryproduct.model.pojo.Category;
import com.xiaxinyu.cloudmall.categoryproduct.model.request.AddCategoryReq;
import com.xiaxinyu.cloudmall.categoryproduct.model.vo.CategoryVO;

import java.util.List;

/**
 * 分类目录Service
 */
public interface CategoryService {
    void add(AddCategoryReq addCategoryReq);

    void update(Category updateCategory);

    void delete(Integer id);

    PageInfo listForAdmin(Integer pageNum, Integer pageSize);

    List<CategoryVO> listForCustomer(Integer parentId);
}
