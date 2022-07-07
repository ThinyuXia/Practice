package com.xiaxinyu.spring.ioc.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

//组件类型注解默认beanId为类名首字母小写,即beadId = userDao
@Repository("uDao")

public class UserDao implements IUserDao{
    public UserDao(){
        System.out.println("正在创建UserDao:" + this);
    }

    public void insert(){
        System.out.println("新增用户数据");
    }
}
