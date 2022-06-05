package com.innovation.wxprogram.controller;

import com.alibaba.fastjson.JSON;
import com.innovation.wxprogram.service.UserService;
import com.innovation.wxprogram.utils.WxUtils;
import com.innovation.wxprogram.vo.ResultVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="WXServlet",urlPatterns = "/data/*")
public class WXServlet extends HttpServlet {

    private static UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String uri = req.getRequestURI();
        String methodName = req.getRequestURI().substring(uri.lastIndexOf('/') + 1);

        //获取body体中内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String encode = "utf-8";
        BufferedReader in = new BufferedReader(new InputStreamReader(
                req.getInputStream(), encode));
        String data = "";
        String line;
        while ((line = in.readLine()) != null) {
            data = data + line;
        }
        in.close();

        Map<String,String> map = (Map)JSON.parse(data);
        String sessionKey = map.get("session_key");
        String encryptedData = map.get("encrypted_data");
        String iv = map.get("iv");

        ResultVO result = new ResultVO();
        if(methodName.equals("wxrun")){
            result.setCode(200);
            result.setMsg("success");
            result.setData((Map<String,String>)JSON.parse(getWxRun(sessionKey,encryptedData,iv)));
            resp.getWriter().println(JSON.toJSONString(result));
        }

    }
    /**
     * 获取微信步数
     */
    public String getWxRun(String sessionKey,String encryptedData,String iv){
        return WxUtils.decryptWeChatRunInfo(sessionKey,encryptedData,iv);
    }


}