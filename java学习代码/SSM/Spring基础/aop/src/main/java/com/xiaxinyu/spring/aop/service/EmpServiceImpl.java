package com.xiaxinyu.spring.aop.service;

public class EmpServiceImpl implements EmpService{
    @Override
    public void createEmp() {
        System.out.println("执行创建员工业务逻辑");
    }
}
