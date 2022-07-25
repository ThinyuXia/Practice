package com.xiaxinyu.mall.controller;

import com.xiaxinyu.mall.common.ApiRestResponse;
import com.xiaxinyu.mall.common.Constant;
import com.xiaxinyu.mall.exception.ExceptionEnum;
import com.xiaxinyu.mall.exception.ExceptionUnify;
import com.xiaxinyu.mall.model.pojo.User;
import com.xiaxinyu.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.Cacheable;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;

/**
 * 用户控制器
 */
@Controller
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/test")
    @ResponseBody
    public User personalPage() {
        return userService.getUser();
    }

    @PostMapping("/register")
    @ResponseBody
    public ApiRestResponse register(@RequestParam("userName") String username, @RequestParam("password") String password) throws ExceptionUnify, NoSuchAlgorithmException {
        if (username == null || username.equals("")) {
            return ApiRestResponse.error(ExceptionEnum.NEED_USER_NAME);
        }

        if (password == null || password.equals("")) {
            return ApiRestResponse.error(ExceptionEnum.NEED_PASSWORD);
        }

        if (password.length() < 8) {
            return ApiRestResponse.error(ExceptionEnum.PASSWORD_TOO_SHORT);
        }

        userService.register(username, password);
        return ApiRestResponse.success();
    }

    @PostMapping("/login")
    @ResponseBody
    public ApiRestResponse login(@RequestParam("userName") String username, @RequestParam("password") String password, HttpSession session) throws ExceptionUnify, NoSuchAlgorithmException {
        if (username == null || username.equals("")) {
            return ApiRestResponse.error(ExceptionEnum.NEED_USER_NAME);
        }

        if (password == null || password.equals("")) {
            return ApiRestResponse.error(ExceptionEnum.NEED_PASSWORD);
        }

        User user = userService.login(username,password);
        //保存用户信息时，不保存密码
        user.setPassword(null);
        session.setAttribute(Constant.MALL_USER,user);
        return ApiRestResponse.success(user);
    }

    @PostMapping("/user/update")
    @ResponseBody
    public ApiRestResponse updateUserInfo(HttpSession session,@RequestParam  String signature) throws ExceptionUnify {
        User currentUser = (User)session.getAttribute(Constant.MALL_USER);
        if(currentUser == null){
            return ApiRestResponse.error(ExceptionEnum.NEED_LOGIN);
        }

        User user = new User();
        user.setId((currentUser.getId()));
        user.setPersonalizedSignature(signature);
        userService.updateInfo(user);
        return ApiRestResponse.success();
    }

    @PostMapping("/user/logout")
    @ResponseBody
    public ApiRestResponse logout(HttpSession session){
        session.removeAttribute(Constant.MALL_USER);
        return ApiRestResponse.success();
    }

    @PostMapping("/adminLogin")
    @ResponseBody
    public ApiRestResponse adminLogin(@RequestParam("userName") String username, @RequestParam("password") String password, HttpSession session) throws ExceptionUnify, NoSuchAlgorithmException {
        if (username == null || username.equals("")) {
            return ApiRestResponse.error(ExceptionEnum.NEED_USER_NAME);
        }

        if (password == null || password.equals("")) {
            return ApiRestResponse.error(ExceptionEnum.NEED_PASSWORD);
        }

        User user = userService.login(username,password);
        //校验用户是否为管理员
        if (userService.checkAdminRole(user)) {
            //保存用户信息时，不保存密码
            user.setPassword(null);
            session.setAttribute(Constant.MALL_USER,user);
            return ApiRestResponse.success(user);
        }else{
            return ApiRestResponse.error(ExceptionEnum.NEED_ADMIN);
        }
    }

}
