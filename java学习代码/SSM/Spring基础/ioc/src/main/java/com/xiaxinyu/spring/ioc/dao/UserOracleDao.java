package com.xiaxinyu.spring.ioc.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary //注入时存在多个相同类型的对象时，注入带有该注解的对象
public class UserOracleDao implements IUserDao{
    public UserOracleDao() {
        System.out.println("正在创建UserOracleDao：" + this);
    }
}
