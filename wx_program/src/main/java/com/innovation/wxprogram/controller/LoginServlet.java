package com.innovation.wxprogram.controller;
import com.alibaba.fastjson.JSON;
import com.innovation.wxprogram.dao.UserDao;
import com.innovation.wxprogram.dto.UserDTO;
import com.innovation.wxprogram.entity.User;
import com.innovation.wxprogram.service.UserService;
import com.innovation.wxprogram.utils.RequestUtils;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 本地缓存中不存在session_key
 */
@WebServlet(name="LoginServlet",urlPatterns = "/check_login")
public class LoginServlet extends HttpServlet {

    private static UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String code = req.getParameter("code");
        String nickName = req.getParameter("nickName");

        Map json = (Map)JSON.parse(RequestUtils.getOpenId(code));
        String openid = (String)json.get("openid");
        String sessionKey = (String) json.get("session_key");

        //说明当前登陆用户是新用户
        if(userService.selectByOpenid(openid) == null && openid != null){
            User user = new User();
            user.setOpenid(openid);
            user.setNickName(nickName);
            user.setSessionKey(sessionKey);
            user.setSumDistance(0);
            userService.insert(user);
        }

        Map result = new HashMap<>();
        result.put("code",200);
        result.put("msg","success");
        result.put("session",sessionKey);
        resp.getWriter().println(JSON.toJSONString(result));
    }
}
