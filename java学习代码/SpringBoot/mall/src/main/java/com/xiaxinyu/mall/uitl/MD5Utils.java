package com.xiaxinyu.mall.uitl;

import com.xiaxinyu.mall.common.Constant;
import org.apache.tomcat.util.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * MD5加密工具
 */
public class MD5Utils {
    public static String getMD5String(String strValue) throws NoSuchAlgorithmException {
        strValue += Constant.SALT;
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        return Base64.encodeBase64String(md5.digest(strValue.getBytes()));
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(getMD5String("1234"));
    }
}
