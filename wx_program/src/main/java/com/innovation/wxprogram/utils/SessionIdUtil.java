package com.innovation.wxprogram.utils;

import java.util.Random;

public class SessionIdUtil {
    //生成唯一的主键
    //格式：时间 + 随机数


    //防止高并发时出现重复主键
    public static synchronized String genUniqueKey(){
        Random random = new Random();

        System.currentTimeMillis();

        Integer num = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(num);
    }
}
