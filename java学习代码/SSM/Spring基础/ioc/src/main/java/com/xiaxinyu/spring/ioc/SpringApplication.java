package com.xiaxinyu.spring.ioc;

import com.xiaxinyu.spring.ioc.entity.Apple;
import com.xiaxinyu.spring.ioc.entity.Child;
import com.xiaxinyu.spring.ioc.entity.Company;
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


        //1.当有多个类型相同的匿名bean标签时，IoC容器中他们的标识符为类名全称#{数字(从0开始)}
        //2.使用context.getBean("类全称")方法获取的是第一个bean标签对象
        //3.想获取后续的对象需要加上#以及编号
        Apple apple1 = context.getBean("com.xiaxinyu.spring.ioc.entity.Apple",Apple.class);
        System.out.println(apple1.getTitle());

        Company company = context.getBean("company",Company.class);
        System.out.println(company);


        System.out.println("========================");

        //获取容器内所有beanId的数组
        String[] beanNames = context.getBeanDefinitionNames();
        for(String beanName : beanNames){
            System.out.println(beanName);
            System.out.println("类型：" + context.getBean(beanName).getClass().getName());
            System.out.println("类型：" + context.getBean(beanName).toString());
        }

    }
}
