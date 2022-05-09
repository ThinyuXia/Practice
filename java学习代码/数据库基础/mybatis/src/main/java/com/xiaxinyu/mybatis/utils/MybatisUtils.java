package com.xiaxinyu.mybatis.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;


/**
 * MybatisUtils工具类创建全局唯一的SqlSessionFactory对象
 */
public class MybatisUtils {
    //利用static使SqlSessionFactory全局唯一
    private static SqlSessionFactory sqlSessionFactory = null;
    //利用静态块初始化类时实例化SqlSessionFactory对象

    static {
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
            //初始化错误时，抛出异常
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * 创建一个新的SqlSession对象
     * @return SqlSession对象
     */
    public static SqlSession openSession(){
        return sqlSessionFactory.openSession();
    }

    /**
     * 释放一个有效的SqlSession对象
     * @param session SqlSession对象
     */
    public static void closeSession(SqlSession session){
        if(session != null){
            session.close();
        }
    }

}
