package com.xiaxinyu.service;

import com.xiaxinyu.entity.Member;
import com.xiaxinyu.entity.management.User;

public interface UserService {
    /**
     *
     * @param username 用户名
     * @param password 密码
     * @return 登陆对象
     */
    public User checkLogin(String username, String password);

}
