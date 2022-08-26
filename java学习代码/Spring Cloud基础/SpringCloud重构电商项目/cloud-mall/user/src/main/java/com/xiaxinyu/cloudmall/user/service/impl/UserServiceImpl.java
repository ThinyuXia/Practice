package com.xiaxinyu.cloudmall.user.service.impl;

import com.xiaxinyu.cloudmall.common.exception.ExceptionEnum;
import com.xiaxinyu.cloudmall.common.exception.ExceptionUnify;
import com.xiaxinyu.cloudmall.user.model.dao.UserMapper;
import com.xiaxinyu.cloudmall.user.model.pojo.User;
import com.xiaxinyu.cloudmall.common.util.MD5Utils;
import com.xiaxinyu.cloudmall.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;

/**
 * UserService实现类
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;


    @Override
    public void register(String userName, String password) throws ExceptionUnify, NoSuchAlgorithmException {
        //查询用户名是否唯一
        User user = userMapper.selectByName(userName);
        if(user != null) {
            throw new ExceptionUnify(ExceptionEnum.NAME_EXISTED);
        }
        User nUser = new User();
        nUser.setUsername(userName);
        nUser.setPassword(MD5Utils.getMD5String(password));
        int count = userMapper.insertSelective(nUser);
        if(count == 0){
            throw new ExceptionUnify(ExceptionEnum.INSERT_FAIL);
        }
    }

    @Override
    public User login(String username, String password) throws ExceptionUnify {
        String md5Password = null;
        try {
            md5Password = MD5Utils.getMD5String(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        User user = userMapper.selectLogin(username,md5Password);
        if(user == null){
            throw new ExceptionUnify(ExceptionEnum.WRONG_PASSWORD);
        }
        return user;
    }

    @Override
    public void updateInfo(User user) throws ExceptionUnify {
        int count = userMapper.updateByPrimaryKeySelective(user);
        if(count != 1){
            throw new ExceptionUnify(ExceptionEnum.UPDATE_FAILED);
        }
    }

    @Override
    public boolean checkAdminRole(User user){
        //1.是普通用户 2是管理员
        return user.getRole().equals(2);
    }
}
