package com.xiaxinyu.OA.controller;

import com.alibaba.fastjson.JSON;
import com.xiaxinyu.OA.entity.User;
import com.xiaxinyu.OA.service.UserService;
import com.xiaxinyu.OA.service.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="LoginServlet",urlPatterns = "/check_login")
public class LoginServlet extends HttpServlet {

    Logger logger = LoggerFactory.getLogger(LoginServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Map<String,Object> result = new HashMap<>();
        UserService userService = new UserService();
        //调用业务逻辑
        try {
            User user = userService.checkLogin(username,password);
            //向session存入登录用户信息，属性名：login_user
            req.getSession().setAttribute("login_user",user);
            result.put("code","0");
            result.put("message","success");
            result.put("redirect_url","/index");
        }catch (BusinessException ex){
            logger.error(ex.getMsg(),ex);
            result.put("code",ex.getCode());
            result.put("message",ex.getMessage());
        }
        catch (Exception e) {
            logger.error(e.getMessage(),e);
            result.put("code",e.getClass().getSimpleName());
            result.put("message",e.getMessage());
        }
        //返回对应结果
        String json = JSON.toJSONString(result);
        resp.getWriter().println(json); //将json字符串向客户端返回
    }
}
