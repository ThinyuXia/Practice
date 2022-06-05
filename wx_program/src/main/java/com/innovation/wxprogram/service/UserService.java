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
        return (User) MybatisUtils.executeQuery(sqlSession -> {
            return sqlSession.getMapper(UserDao.class).selectByOpenid(openid);
        });
    }

    public User selectBySessionKey(String sessionKey){
        return (User) MybatisUtils.executeQuery(sqlSession -> {
            return sqlSession.getMapper(UserDao.class).selectBySessionKey(sessionKey);
        });
    }

    public void insert(User user){
        MybatisUtils.executeUpdate(sqlSession -> {
            sqlSession.getMapper(UserDao.class).insert(user);
            return null;
        });
    }

    public void updateSession(User user){
        MybatisUtils.executeUpdate(sqlSession -> {
            sqlSession.getMapper(UserDao.class).updateSession(user);
            return null;
        });
    }

    public void updateUserInfo(User user){
        MybatisUtils.executeUpdate(sqlSession -> {
            sqlSession.getMapper(UserDao.class).updateUserInfo(user);
            return null;
        });
    }

}
