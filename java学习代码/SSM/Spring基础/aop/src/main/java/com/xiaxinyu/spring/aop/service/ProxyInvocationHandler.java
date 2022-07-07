package com.xiaxinyu.spring.aop.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * InvocationHandler是JDK提供的反射类，用于在JDK动态代理中对目标方法进行增强
 * InvocationHandler实现类与切面类的环绕通知类似
 */
public class ProxyInvocationHandler implements InvocationHandler {
    private  Object target;//目标对象
    private ProxyInvocationHandler(Object target){
        this.target = target;
    }
    /**
     * 在invoke()方法中对目标方法
     * @param proxy 代理类对象
     * @param method 目标方法对象
     * @param args 目标方法参数
     * @return 目标方法运行后返回值
     * @throws Throwable 目标方法抛出的异常
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("====" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date()) + "==========");
        Object ret = method.invoke(target,args); //调用目标方法，类似ProceedingJoinPoint.proceed()
        return ret;
    }

    public static void main(String[] args) {
        //动态代理，必须实现接口才可以运行
        US us = new USImpl();
        ProxyInvocationHandler invocationHandler = new ProxyInvocationHandler(us);
        //动态创建代理类
        US usProxy = (US)Proxy.newProxyInstance(us.getClass().getClassLoader(),
                us.getClass().getInterfaces(),
                invocationHandler);
        usProxy.createUser();
        EmpService empService = new EmpServiceImpl();
        EmpService empService1 = (EmpService)Proxy.newProxyInstance(empService.getClass().getClassLoader(),
                empService.getClass().getInterfaces(),
                new ProxyInvocationHandler(empService));
        empService1.createEmp();
    }
}
