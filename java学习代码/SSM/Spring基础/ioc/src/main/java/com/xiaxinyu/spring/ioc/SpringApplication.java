package com.xiaxinyu.spring.ioc;

import com.xiaxinyu.spring.ioc.entity.Apple;
import com.xiaxinyu.spring.ioc.entity.Child;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApplication {
    public static void main(String[] args) {
        String[] configLocations = {"classpath:applicationContext-1.xml","classpath:applicationContext.xml"};

        //创建Spring IoC容器，并根据配置文件实例化对象放到容器中
        ApplicationContext context = new ClassPathXmlApplicationContext(configLocations);
//        Apple sweetApple = context.getBean("sweetApple", Apple.class);
//        System.out.println(sweetApple.getTitle());
//        Child lily = context.getBean("lily",Child.class);
//        lily.eat();
//        System.out.println(lily.getName());
//
//        //一个bean中拿到的是同一个对象
//        Child lily1 = context.getBean("lily",Child.class);
//        lily1.setName("222");
//        System.out.println(lily.getName());
//
//        Child andy = context.getBean("andy",Child.class);
//        andy.eat();
//
//        Child luna = context.getBean("luna",Child.class);
//        luna.eat();
        Apple apple = context.getBean("sweetApple",Apple.class);
        System.out.println(apple.getTitle());

        Apple apple1 = context.getBean("com.xiaxinyu.spring.ioc.entity.Apple",Apple.class);
        System.out.println(apple1.getTitle());


    }
}
