package com.xiaxinyu.spring;

import com.xiaxinyu.spring.ioc.context.ApplicationContext;
import com.xiaxinyu.spring.ioc.context.ClassPathXmlApplicationContext;

public class MyApplication {
    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext();
        Object obj = context.getBean("sweetApple");
        System.out.println();
    }
}
