package com.xiaxinyu.OA.dao;

import com.xiaxinyu.OA.entity.User;
import com.xiaxinyu.OA.utils.MybatisUtils;

/**
 * 用户表Dao
 */
public class UserDao {
    /**
     * 按用户名查询用户表
     * @param username 用户名
     * @return User对象，包含对应的用户信息，null则不存在该用户
     */
    public User selectByUsername(String username){
       return (User)MybatisUtils.executeQuery(sqlSession -> sqlSession.selectOne("usermapper.selectByUsername",username));
    }
}
