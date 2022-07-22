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


    /**
     * 更新图书评分，评价数量
     */
    public void updateEvaluation();

    /**
     * 新增图书
     * @param book
     * @return
     */
    public Book createBook(Book book);

    /**
     * 更新图书
     * @param book
     * @return
     */
    public Book updateBook(Book book);

    /**
     * 删除图书及相关数据
     * @param bookId
     */
    public void deleteBook(Long bookId);
}
