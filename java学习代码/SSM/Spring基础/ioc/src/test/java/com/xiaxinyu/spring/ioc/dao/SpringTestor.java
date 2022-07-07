package com.xiaxinyu.spring.ioc.dao;

import com.xiaxinyu.spring.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class) //将Junit4的执行权交给Spring Test，在测试用例执行前自动初始化IoC容器
@ContextConfiguration(locations = {"classpath:applicationContext-2.xml"})
public class SpringTestor {

    @Resource
    private UserService userService;

    @Test
    public void testUserService(){
        System.out.println(111);
    }

}