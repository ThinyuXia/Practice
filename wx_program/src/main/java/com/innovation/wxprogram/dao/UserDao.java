package com.innovation.wxprogram.dao;

import com.innovation.wxprogram.entity.User;
import com.innovation.wxprogram.utils.MybatisUtils;

public interface UserDao {
    public User selectByOpenid(String openid);
    public User selectBySessionKey(String sessionKey);
    public void insert(User user);
    public void updateSession(User user);
}
