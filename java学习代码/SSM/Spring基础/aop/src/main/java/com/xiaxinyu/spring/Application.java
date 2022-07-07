package com.xiaxinyu.spring;

import com.xiaxinyu.spring.aop.service.*;

public class Application {
    public static void main(String[] args) {
        US us = new USProxy1(new USProxy(new USImpl()));
        us.createUser();
    }
}
