package com.xiaxinyu.handler;

import com.alibaba.fastjson.JSON;
import com.xiaxinyu.domain.ResponseResult;
import com.xiaxinyu.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description:
 * @author: xiaxinyu
 * @Email: xiaxinyuxy@163.com
 * @date: 2022年11月21日 09:55
 * @Copyright: 个人版权所有
 * @version: 1.0.0
 */

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResponseResult result = new ResponseResult(HttpStatus.UNAUTHORIZED.value(),"用户认证失败请重新登录");
        String json = JSON.toJSONString(result);       //处理异常
        WebUtils.renderString(httpServletResponse,json);
    }
}