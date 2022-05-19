package com.xiaxinyu.spring.ioc.bookshop.dao;

public class BookDaoOracleImpl implements BookDao{
    @Override
    public void insert() {
        System.out.println("向Oracle数据库中book表插入一条数据");
    }
}
