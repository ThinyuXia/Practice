package com.innovation.wxprogram.controller;

import com.alibaba.fastjson.JSON;
import com.innovation.wxprogram.dto.UserDTO;
import com.innovation.wxprogram.entity.User;
import com.innovation.wxprogram.enums.ResultEnum;
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

        ResultVO result = new ResultVO();
        if(openid == null){
            result.setCode(ResultEnum.CODE_ERROR.getCode());
            result.setMsg(ResultEnum.CODE_ERROR.getMsg());
            resp.getWriter().println(JSON.toJSONString(result));
            return;
        }

        if(code == null || code.equals("")){
            result.setCode(ResultEnum.PARAM_ERROR.getCode());
            result.setMsg(ResultEnum.PARAM_ERROR.getMsg());
            resp.getWriter().println(JSON.toJSONString(result));
        }else{
            User user = new User();
            user.setOpenid(openid);
            user.setSessionKey(sessionKey);
            userService.updateSession(user);

            result.setMsg("session_key更新成功");
            result.setCode(200);
            result.setData(sessionKey);

            resp.getWriter().println(JSON.toJSONString(result));
        }





    }

}
