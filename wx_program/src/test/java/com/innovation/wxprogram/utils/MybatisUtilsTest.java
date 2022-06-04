package com.innovation.wxprogram.utils;

import junit.framework.TestCase;

public class MybatisUtilsTest extends TestCase {

    public void testExecuteQuery() {
        String result = (String)MybatisUtils.executeQuery(sqlSession -> {
            return (String)sqlSession.selectOne("test.sample");
        });
        System.out.println(result);
    }
}