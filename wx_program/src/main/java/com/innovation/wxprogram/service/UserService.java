package com.innovation.wxprogram.service;

import com.alibaba.fastjson.JSON;
import com.innovation.wxprogram.dao.UserDao;
import com.innovation.wxprogram.entity.User;
import com.innovation.wxprogram.utils.MybatisUtils;
import com.innovation.wxprogram.utils.RequestUtils;

public class UserService {
    /**
     * 按照openid查询用户对象
     * @param openid
     * @return
     */
    public User selectByOpenid(String openid){
        return (User) MybatisUtils.executeQuery(sqlSession -> sqlSession.getMapper(UserDao.class).selectByOpenid(openid));
    }

    public User selectBySessionId(String sessionId){
        return (User) MybatisUtils.executeQuery(sqlSession -> sqlSession.getMapper(UserDao.class).selectBySessionId(sessionId));
    }

    public void insert(User user){
        MybatisUtils.executeUpdate(sqlSession -> {
            sqlSession.getMapper(UserDao.class).insert(user);
            return null;
        });
    }


    public void updateUserInfo(User user){
        MybatisUtils.executeUpdate(sqlSession -> {
            sqlSession.getMapper(UserDao.class).updateUserInfo(user);
            return null;
        });
    }

    public void updateUserDistance(User user){
        MybatisUtils.executeUpdate(sqlSession -> {
            sqlSession.getMapper(UserDao.class).updateDistance(user);
            return null;
        });
    }
}
