package com.xiaxinyu.spring.aop;

import com.xiaxinyu.spring.aop.homework.BookShop;
import com.xiaxinyu.spring.aop.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApplication {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
//        UserService userService = context.getBean("userService", UserService.class);
//        userService.createUser();
//        userService.generateRandomPassword("MD5",16);
        ((BookShop)context.getBean("bookShop")).sellingBooks();

    }
}
