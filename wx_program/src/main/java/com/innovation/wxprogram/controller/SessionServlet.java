package com.innovation.wxprogram.controller;

import com.alibaba.fastjson.JSON;
import com.innovation.wxprogram.dto.UserDTO;
import com.innovation.wxprogram.entity.Session;
import com.innovation.wxprogram.entity.User;
import com.innovation.wxprogram.enums.ResultEnum;
import com.innovation.wxprogram.service.SessionService;
import com.innovation.wxprogram.service.UserService;
import com.innovation.wxprogram.utils.RequestUtils;
import com.innovation.wxprogram.vo.ResultVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*更新session_key*/
@WebServlet(name="SessionServlet",urlPatterns = "/session/*")
public class SessionServlet extends HttpServlet {

    private UserService userService = new UserService();
    private SessionService sessionService = new SessionService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String uri = req.getRequestURI();
        String methodName = req.getRequestURI().substring(uri.lastIndexOf('/') + 1);


        String sessionId = req.getParameter("session_id");
        String code = req.getParameter("code");

        Map json = (Map) JSON.parse(RequestUtils.getOpenId(code));
        String openid = (String)json.get("openid");
        String sessionKey = (String) json.get("session_key");


        ResultVO result = new ResultVO();
        if(code == null || code.equals("")){
            result.setCode(ResultEnum.PARAM_ERROR.getCode());
            result.setMsg(ResultEnum.PARAM_ERROR.getMsg());
            return;
        }
        if(methodName.equals("update")){
            if(openid == null){
                result.setCode(ResultEnum.CODE_ERROR.getCode());
                result.setMsg(ResultEnum.CODE_ERROR.getMsg());
                resp.getWriter().println(JSON.toJSONString(result));
                return;
            }
            else{
                Session session = new Session();
                session.setSessionId(sessionId);
                session.setSessionKey(sessionKey);
                sessionService.update(session);
                result.setMsg("session_key更新成功");
                result.setCode(200);
            }
            resp.getWriter().println(JSON.toJSONString(result));
        }else if(methodName.equals("session_id")){
                if (openid == null){
                    result.setCode(ResultEnum.CODE_ERROR.getCode());
                    result.setMsg(ResultEnum.CODE_ERROR.getMsg());
                    resp.getWriter().println(JSON.toJSONString(result));
                    return;
                }

                User user = userService.selectByOpenid(openid);
                if(user == null){
                    result.setMsg("该用户是新用户,请调用注册方法");
                    result.setCode(200);
                    result.setData(null);
                }else{
                    result.setMsg("success");
                    result.setCode(200);
                    result.setData(user.getSessionId());
                }
                resp.getWriter().println(JSON.toJSONString(result));
        }


    }



}
