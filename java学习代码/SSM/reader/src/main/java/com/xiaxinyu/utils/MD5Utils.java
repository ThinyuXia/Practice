package com.xiaxinyu.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Utils {
    public static String MD5Digest(String source,Integer salt){
        char[] ca = source.toCharArray();
        //加盐混淆原数据
        for(int i = 0;i < ca.length;i ++){
            ca[i] = (char)(ca[i] + salt);
        }
        String target = new String(ca);
        return DigestUtils.md5Hex(target);
    }
}
