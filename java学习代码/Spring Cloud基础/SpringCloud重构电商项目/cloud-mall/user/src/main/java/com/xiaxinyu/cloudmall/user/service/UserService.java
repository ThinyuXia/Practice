package com.xiaxinyu.cloudmall.user.service;



import com.xiaxinyu.cloudmall.common.exception.ExceptionUnify;
import com.xiaxinyu.cloudmall.user.model.pojo.User;

import java.security.NoSuchAlgorithmException;

/**
 * UserService
 */

public interface UserService {

    public void register(String userName,String password) throws ExceptionUnify, NoSuchAlgorithmException;

    User login(String username, String password) throws ExceptionUnify;

    void updateInfo(User user) throws ExceptionUnify;

    boolean checkAdminRole(User user);
}
