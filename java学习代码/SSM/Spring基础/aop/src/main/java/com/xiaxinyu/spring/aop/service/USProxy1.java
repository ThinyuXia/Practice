package com.xiaxinyu.spring.aop.service;

import java.text.SimpleDateFormat;
import java.util.Date;

public class USProxy1 implements US{
    //持有委托类的对象
    private US us;
    public USProxy1(US us){
        this.us = us;
    }

    @Override
    public void createUser() {
        us.createUser();
        System.out.println("====后置扩展功能======");
    }

}
