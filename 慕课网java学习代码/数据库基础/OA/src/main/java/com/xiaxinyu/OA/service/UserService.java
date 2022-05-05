package com.xiaxinyu.OA.service;

import com.xiaxinyu.OA.dao.RBACDao;
import com.xiaxinyu.OA.dao.UserDao;
import com.xiaxinyu.OA.entity.Node;
import com.xiaxinyu.OA.entity.User;
import com.xiaxinyu.OA.service.exception.BusinessException;
import com.xiaxinyu.OA.utils.MD5Utils;

import java.util.List;

public class UserService {
    private UserDao userDao = new UserDao();
    private RBACDao rbacDao = new RBACDao();

    /**
     * 根据前台输入进行登陆校验
     * @param username 前台输入的用户名
     * @param password 前台输入的密码
     * @return 校验通过后，返回包含对应用户数据的User实体类
     * @throws BusinessException L001-用户名不存在，L002-密码错误
     */
    public User checkLogin(String username, String password){
        //按用户名查询用户
        User user = userDao.selectByUsername(username);
        if(user == null){
            //抛出用户不存在异常
            throw new BusinessException("L001","用户名不存在");
        }
        if(! user.getPassword().equals(MD5Utils.MD5Digest(password,user.getSalt()))){
            //抛出密码错误异常
            throw new BusinessException("L002","密码错误");
        }

        return user;
    }

    public List<Node> selectNodeByUserId(Long userId){
        return rbacDao.selectNodeByUserId(userId);
    }

}
