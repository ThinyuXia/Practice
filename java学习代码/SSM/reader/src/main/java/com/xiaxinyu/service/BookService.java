package com.xiaxinyu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaxinyu.entity.Book;

public interface BookService {
    /**
     * 分页查询图书
     * @param page 页号
     * @param rows 每页记录数
     * @return 分页对象
     */
    public IPage<Book> paging(Long categoryId,String order,Integer page, Integer rows);


    /**
     * 根据图书编号查询图书对西那个
     * @param id 图书编号
     * @return 图书对象
     */
    public Book selectById(Long id);
}
