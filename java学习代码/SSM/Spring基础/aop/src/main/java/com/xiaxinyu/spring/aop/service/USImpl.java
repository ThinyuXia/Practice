package com.xiaxinyu.spring.aop.service;

public class USImpl implements US{
    @Override
    public void createUser() {
        System.out.println("执行创建用户业务逻辑");
    }
}
