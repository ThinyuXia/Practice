package com.xiaxinyu.spring.aop.homework;

import org.springframework.stereotype.Component;

@Component
public class BookShop {
    public void sellingBooks(){
        System.out.println("卖出一本书");
    }
}
