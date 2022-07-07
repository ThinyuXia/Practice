package com.xiaxinyu.spring.aop.homework;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MethodPro {
//    public void welcome(ProceedingJoinPoint pjp) throws Throwable {
//        System.out.println("欢迎光临小店");
//        pjp.proceed();
//        System.out.println("欢迎再次光临");
//    }
    @Before("execution(public * com.xiaxinyu..*.*(..))")
    public void preSales(){
        System.out.println("调用售前方法");
    }

    @After("execution(public * com.xiaxinyu..*.*(..))")
    public void afterSales(){
        System.out.println("调用售后方法");
    }

}
