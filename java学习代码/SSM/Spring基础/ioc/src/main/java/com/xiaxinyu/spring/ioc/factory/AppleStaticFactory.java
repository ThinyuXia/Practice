package com.xiaxinyu.spring.ioc.factory;

import com.xiaxinyu.spring.ioc.entity.Apple;

public class AppleStaticFactory {
    public static Apple createSweetApple(){
        Apple apple = new Apple();
        apple.setTitle("红富士");
        apple.setOrigin("欧洲");
        apple.setColor("红色");
        return apple;
    }
}
