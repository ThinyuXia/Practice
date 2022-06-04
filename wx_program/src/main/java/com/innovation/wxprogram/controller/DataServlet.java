package com.innovation.wxprogram.controller;

import com.alibaba.fastjson.JSON;
import com.innovation.wxprogram.entity.User;
import com.innovation.wxprogram.service.UserService;
import com.innovation.wxprogram.utils.RequestUtils;
import com.innovation.wxprogram.utils.WxUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="DataServlet",urlPatterns = "/data/*")
public class DataServlet extends HttpServlet {

    private static UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");


        String sessionKey = req.getParameter("iv");
        String iv = req.getParameter("session_key");
        String encryptedData = req.getParameter("encrypted_data");

        System.out.println(iv);
        System.out.println(sessionKey);

        String uri = req.getRequestURI();
//        System.out.println(uri);

        String methodName = req.getRequestURI().substring(uri.lastIndexOf('/') + 1);


        if(methodName.equals("wxrun")){
            resp.getWriter().println(getWxRun(sessionKey,encryptedData,iv));
        }

    }

    /**
     * 获取微信步数
     */
    public String getWxRun(String sessionKey,String encryptedData,String iv){
        return WxUtils.decryptWeChatRunInfo(sessionKey,encryptedData,iv);
    }

}