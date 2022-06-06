package com.innovation.wxprogram.controller;

import com.alibaba.fastjson.JSON;
import com.innovation.wxprogram.entity.User;
import com.innovation.wxprogram.enums.ResultEnum;
import com.innovation.wxprogram.service.UserService;
import com.innovation.wxprogram.utils.PostUtils;
import com.innovation.wxprogram.utils.WxUtils;
import com.innovation.wxprogram.vo.ResultVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name="UserServlet",urlPatterns = "/data/*")
public class UserServlet extends HttpServlet {

    private static UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String uri = req.getRequestURI();
        String methodName = req.getRequestURI().substring(uri.lastIndexOf('/') + 1);

        //获取body体中内容
        Map<String,String> data = PostUtils.getBody(req);
        String sessionKey = data.get("session_key");

        ResultVO result = new ResultVO();

        if(sessionKey == null || sessionKey.equals("")){
            result.setCode(ResultEnum.SESSION_KEY_ERROR.getCode());
            result.setMsg(ResultEnum.SESSION_KEY_ERROR.getMsg());
            resp.getWriter().println(JSON.toJSONString(result));
            return;
        }

        if(methodName.equals("wxrun")){
            String encryptedData = data.get("encrypted_data");
            String iv = data.get("iv");
            result.setCode(200);
            result.setMsg("success");
            result.setData((Map<String,String>)JSON.parse(getWxRun(sessionKey,encryptedData,iv)));
            resp.getWriter().println(JSON.toJSONString(result));
        }else if(methodName.equals("update_userinfo")){
            String nickName = data.getOrDefault("nick_name",null);
            String realName = data.getOrDefault("real_name",null);
            String phoneNumber = data.getOrDefault("phone_number",null);
            updateUserInfo(sessionKey,realName,nickName,phoneNumber);
            result.setMsg("success");
            result.setCode(200);
            resp.getWriter().println(JSON.toJSONString(result));
        }else if(methodName.equals("update_distance")){
            Integer distance = Integer.parseInt(data.getOrDefault("sum_distance",null));
            if(distance == null){
                result.setCode(ResultEnum.PARAM_ERROR.getCode());
                result.setMsg(ResultEnum.PARAM_ERROR.getMsg());
                resp.getWriter().println(JSON.toJSONString(result));
                return;
            }
            updateDistance(sessionKey,distance);
            result.setMsg("success");
            result.setCode(200);
            resp.getWriter().println(JSON.toJSONString(result));
        }

    }
    /**
     * 获取用户微信步数
     */
    public String getWxRun(String sessionKey,String encryptedData,String iv){
        return WxUtils.decryptWeChatRunInfo(sessionKey,encryptedData,iv);
    }

    /**
     * 更新用户个人信息
     */
    public void updateUserInfo(String sessionKey,String realName,String nickName,String phoneNumber){
        User user = userService.selectBySessionKey(sessionKey);
        user.setRealName(realName);
        user.setNickName(nickName);
        user.setPhoneNumber(phoneNumber);
        userService.updateUserInfo(user);
    }

    /**
     * 更新用户总里程
     */

    public void updateDistance(String sessionKey,Integer disistance){
        User user = new User();
        user.setSumDistance(disistance);
        user.setSessionKey(sessionKey);
        userService.updateUserDistance(user);
    }

}