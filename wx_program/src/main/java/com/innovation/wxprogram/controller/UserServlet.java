package com.innovation.wxprogram.controller;

import com.alibaba.fastjson.JSON;
import com.innovation.wxprogram.entity.User;
import com.innovation.wxprogram.service.UserService;
import org.springframework.beans.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserServlet",urlPatterns = "/user/*")
public class UserServlet extends HttpServlet {
    private static UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String uri = req.getRequestURI();
        String methodName = req.getRequestURI().substring(uri.lastIndexOf('/') + 1);
        String sessionKey = req.getParameter("session_key");
        User user = userService.selectBySessionKey(sessionKey);

        if(methodName.equals("info")){
            resp.getWriter().println(JSON.toJSON(user));
        }else if(methodName.equals("update")){
            User newUser = new User();
            BeanUtils.copyProperties(user,newUser);
            userService.updateUserInfo(newUser);
        }

    }

}
