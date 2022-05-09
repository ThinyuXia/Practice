package com.xiaxinyu.OA.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Utils {
    public static String MD5Digest(String source){
        return DigestUtils.md5Hex(source);
    }

    /**
     *
     * @param source
     * @param salt 盐值
     * @return
     */
    public static String MD5Digest(String source,Integer salt){
        char[] c = source.toCharArray();
        for(int i = 0;i < c.length;i ++){
            c[i] = (char)(c[i] + salt);
        }
        String target = new String(c);
        return MD5Digest(target);
    }

}
