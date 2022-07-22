package com.xiaxinyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaxinyu.entity.management.User;
import com.xiaxinyu.mapper.UserMapper;
import com.xiaxinyu.service.UserService;
import com.xiaxinyu.service.exception.BussinessException;
import com.xiaxinyu.utils.MD5Utils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User checkLogin(String username, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User user = userMapper.selectOne(queryWrapper);
        if(user == null){
            throw new BussinessException("M01","用户不存在");
        }
        String md5 = MD5Utils.MD5Digest(password,user.getSalt());
        if(! md5.equals(user.getPassword())){
            throw new BussinessException("M03","输入密码有误");
        }
        return user;
    }
}
