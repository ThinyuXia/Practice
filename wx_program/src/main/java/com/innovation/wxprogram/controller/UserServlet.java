package com.innovation.wxprogram.controller;

import com.alibaba.fastjson.JSON;
import com.innovation.wxprogram.dto.UserDTO;
import com.innovation.wxprogram.entity.User;
import com.innovation.wxprogram.enums.ResultEnum;
import com.innovation.wxprogram.service.SessionService;
import com.innovation.wxprogram.service.UserService;
import com.innovation.wxprogram.utils.PostUtils;
import com.innovation.wxprogram.utils.WxUtils;
import com.innovation.wxprogram.vo.ResultVO;
import org.springframework.beans.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name="UserServlet",urlPatterns = "/data/*")
public class UserServlet extends HttpServlet {

    private UserService userService = new UserService();
    private SessionService sessionService = new SessionService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String uri = req.getRequestURI();
        String methodName = req.getRequestURI().substring(uri.lastIndexOf('/') + 1);

        //获取body体中内容
        Map<String,String> data = PostUtils.getBody(req);
        String sessionId = data.get("session_id");
        String sessionKey = sessionService.selectBySessionId(sessionId);

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
            updateUserInfo(sessionId,realName,nickName,phoneNumber);
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
            updateDistance(sessionId,distance);
            result.setMsg("success");
            result.setCode(200);
            resp.getWriter().println(JSON.toJSONString(result));
        }else if(methodName.equals("info")){ //获取用户信息
            User user = userService.selectBySessionId(sessionId);
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(user,userDTO);
            result.setMsg("success");
            result.setCode(200);
            result.setData(userDTO);
            resp.getWriter().println(JSON.toJSONString(result));
        }else if(methodName.equals("rank")){ //获取排行榜
            //TODO



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
    public void updateUserInfo(String sessionId,String realName,String nickName,String phoneNumber){
        User user = userService.selectBySessionId(sessionId);
        user.setRealName(realName);
        user.setNickName(nickName);
        user.setPhoneNumber(phoneNumber);
        userService.updateUserInfo(user);
    }

    /**
     * 更新用户总里程
     */

    public void updateDistance(String sessionId,Integer distance){
        User user = new User();
        user.setSumDistance(distance);
        user.setSessionId(sessionId);
        userService.updateUserDistance(user);
    }

}