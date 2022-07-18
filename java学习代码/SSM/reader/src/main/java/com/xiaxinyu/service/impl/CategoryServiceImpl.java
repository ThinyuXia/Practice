package com.xiaxinyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaxinyu.entity.Category;
import com.xiaxinyu.mapper.CategoryMapper;
import com.xiaxinyu.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("categoryService") //容器中的beanid通常和接口名称一致
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    /**
     * 查询所有图书分类
     * @return 图书分类list
     */
    @Override
    public List<Category> selectAll() {
        return categoryMapper.selectList(new QueryWrapper<Category>());
    }
}
