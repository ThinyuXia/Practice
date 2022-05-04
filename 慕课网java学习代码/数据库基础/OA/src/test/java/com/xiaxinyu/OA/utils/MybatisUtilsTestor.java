package com.xiaxinyu.OA.utils;

import org.junit.Test;

public class MybatisUtilsTestor {
    @Test
    public void testCase1(){
        String result = (String) MybatisUtils.executeQuery(sqlSession -> (String)sqlSession.selectOne("test.sample"));
        System.out.println(result);
    }
}
