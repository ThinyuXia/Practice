package com.xiaxinyu.spring.ioc;

import com.xiaxinyu.spring.ioc.controller.UserController;
import com.xiaxinyu.spring.ioc.dao.UserDao;
import com.xiaxinyu.spring.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //当前类是一个配置类，用于替代application.xml
public class Config {

    @Bean //Java Config中利用方法创建对象，将方法返回对象放入容器中，beanId = 方法名
    public UserDao userDao(){
        UserDao userDao = new UserDao();
        System.out.println("已创建" + userDao);
        return userDao;
    }

    @Bean //先尝试按name注入，若name不存在则按类型注入
    public UserService userService(UserDao userDao){
        UserService userService = new UserService();
        System.out.println("已创建" + userService);
        userService.setuDao(userDao);
        System.out.println("调用setUserDao" + userDao);
        return userService;
    }

    @Bean
    public UserController userController(UserService userService){
        UserController userController = new UserController();
        System.out.println("已创建" + userController);
        userController.setUserService(userService);
        System.out.println("调用setUserService" + userService);
        return userController;
    }

}
