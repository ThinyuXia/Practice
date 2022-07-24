package com.xiaxinyu.mall.service;

import com.xiaxinyu.mall.exception.ExceptionUnify;
import com.xiaxinyu.mall.model.pojo.User;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

/**
 * UserService
 */

public interface UserService {
    public User getUser();

    public void register(String userName,String password) throws ExceptionUnify, NoSuchAlgorithmException;


    User login(String username, String password) throws ExceptionUnify;

    void updateInfo(User user) throws ExceptionUnify;

    boolean checkAdminRole(User user);
}
