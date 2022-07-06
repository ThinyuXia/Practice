package com.xiaxinyu.spring.service;

import com.xiaxinyu.spring.ioc.dao.IUserDao;
import com.xiaxinyu.spring.ioc.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Scope("singleton") //设置单例/多例，与XML中bean scope完全相同
public class UserService {

    @Value("${metaData}") //为属性设置静态数值
    private String metaData;

    public UserService() {
        System.out.println("正在创建UserService：" + this);
    }

    @PostConstruct //XML中bean init-method完全相同
    public void init(){
        System.out.println("初始化UserService对象,metaData=" + metaData);
    }



//    @Autowired
//    如果装配注解放在属性上，SpringIoC容器会自动通过反射技术将属性privaye修饰符自动改成public，直接进行赋值，不再执行set方法
    private IUserDao uDao;

    public IUserDao getuDao() {
        return uDao;
    }

    @Autowired
//    如果装配注解放在set方法上，则自动按类型/名称对set方法参数进行注入
    public void setuDao(IUserDao uDao) {
        System.out.println("setuDao:" + uDao);
        this.uDao = uDao;
    }
}
