package com.innovation.wxprogram.controller;
import com.alibaba.fastjson.JSON;
import com.innovation.wxprogram.dao.UserDao;
import com.innovation.wxprogram.dto.UserDTO;
import com.innovation.wxprogram.entity.User;
import com.innovation.wxprogram.enums.ResultEnum;
import com.innovation.wxprogram.service.UserService;
import com.innovation.wxprogram.utils.RequestUtils;
import com.innovation.wxprogram.vo.ResultVO;
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

        ResultVO result = new ResultVO();
        if(openid == null){
            result.setCode(ResultEnum.CODE_ERROR.getCode());
            result.setMsg(ResultEnum.CODE_ERROR.getMsg());
            resp.getWriter().println(JSON.toJSONString(result));
            return;
        }



        if(code == null || nickName == null || code.equals("") || nickName.equals("")){
            result.setCode(ResultEnum.PARAM_ERROR.getCode());
            result.setMsg(ResultEnum.PARAM_ERROR.getMsg());
            resp.getWriter().println(JSON.toJSONString(result));
        }else if(userService.selectByOpenid(openid) == null){  //说明当前用户是新用户
            User user = new User();
            user.setOpenid(openid);
            user.setNickName(nickName);
            user.setSessionKey(sessionKey);
            user.setSumDistance(0);
            userService.insert(user);
            result.setCode(200);
            result.setMsg("success");
            result.setData(sessionKey);
            resp.getWriter().println(JSON.toJSONString(result));
        }else{
            result.setCode(ResultEnum.LOGIN_ERROR.getCode());
            result.setMsg(ResultEnum.LOGIN_ERROR.getMsg());
            result.setData(sessionKey);
            resp.getWriter().println(JSON.toJSONString(result));
        }


    }
}
