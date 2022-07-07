package com.xiaxinyu.spring.aop.service;

import java.text.SimpleDateFormat;
import java.util.Date;

//静态代理是指必须手动创建代理类的代理模式使用方式
public class USProxy implements US{
    //持有委托类的对象
    private US us;
    public USProxy(US us){
        this.us = us;
    }

    @Override
    public void createUser() {
        System.out.println("====" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date()) + "==========");
        us.createUser();
    }
}
