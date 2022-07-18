package com.xiaxinyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaxinyu.entity.Book;
import com.xiaxinyu.mapper.BookMapper;
import com.xiaxinyu.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("bookService")
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class BookServiceImpl implements BookService {

    @Resource
    private BookMapper bookMapper;

    @Override
    public IPage<Book> paging(Long categoryId, String order, Integer page, Integer rows) {
        Page<Book> p = new Page<>(page, rows);
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        if(categoryId != null && categoryId != -1){
            queryWrapper.eq("category_id",categoryId);
        }
        if(order != null){
            if(order.equals("quantity")){
                queryWrapper.orderByDesc("evaluation_quantity");
            }else if (order.equals("score")){
                queryWrapper.orderByDesc("evaluation_score");
            }
        }
        IPage<Book> pageObject = bookMapper.selectPage(p, queryWrapper);
        return pageObject;
    }


    @Override
    public Book selectById(Long id) {
        return bookMapper.selectById(id);
    }
}
