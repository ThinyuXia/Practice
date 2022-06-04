package com.innovation.wxprogram.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.function.Function;

public class MybatisUtils {

    private static SqlSessionFactory sqlSessionFactory = null;
    static{
        Reader reader = null;
        try{
            reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        }catch (IOException e){
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * 执行select查询sql
     * @param function 执行查询语句的代码块
     * @return 返回查询结果
     */
    public static Object executeQuery(Function<SqlSession,Object> function){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            Object object = function.apply(sqlSession);

            return object;
        }finally {
            sqlSession.close();
        }
    }

    /**
     * 执行DELETE/UPDATE/INSERT
     * @param function 写操作代码块
     * @return 返回写操作执行结果
     */
    public static Object executeUpdate(Function<SqlSession,Object> function){
        SqlSession sqlSession = sqlSessionFactory.openSession(false); //关闭事物自动提交
        try{
            Object object = function.apply(sqlSession);
            sqlSession.commit();
            return object;
        }catch (RuntimeException e){
           sqlSession.rollback();
           throw e;
        }finally {
            sqlSession.close();
        }
    }

}
