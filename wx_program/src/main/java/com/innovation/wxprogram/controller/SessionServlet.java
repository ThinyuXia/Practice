package com.innovation.wxprogram.controller;

import com.alibaba.fastjson.JSON;
import com.innovation.wxprogram.dto.UserDTO;
import com.innovation.wxprogram.entity.User;
import com.innovation.wxprogram.service.UserService;
import com.innovation.wxprogram.utils.RequestUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*更新session_key*/
@WebServlet(name="SessionServlet",urlPatterns = "/session_update")
public class SessionServlet extends HttpServlet {

    private static UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String code = req.getParameter("code");
        Map json = (Map) JSON.parse(RequestUtils.getOpenId(code));
        String openid = (String)json.get("openid");
        String sessionKey = (String) json.get("session_key");

        User user = new User();
        user.setOpenid(openid);
        user.setSessionKey(sessionKey);
        userService.updateSession(user);
        Map<String,String> result = new HashMap<>();
        result.put("code","200");
        result.put("msg","success");
        result.put("session",sessionKey);
        resp.getWriter().println(JSON.toJSONString(result));

    }

}
