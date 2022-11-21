package com.xiaxinyu.service;

import com.xiaxinyu.domain.ResponseResult;
import com.xiaxinyu.domain.User;

public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
