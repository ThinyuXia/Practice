package com.xiaxinyu.spring;


import com.xiaxinyu.spring.ioc.Config;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApplication_2 {
    public static void main(String[] args) {
        //基于Java Config配置IoC容器的初始化
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        String[] ids = context.getBeanDefinitionNames();
        for(String id : ids) System.out.println(id + " : " + context.getBean(id));

    }
}
