package com.xiaxinyu.cloudmall.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.xiaxinyu.cloudmall.common.common.Constant;
import com.xiaxinyu.cloudmall.user.model.pojo.User;
import com.xiaxinyu.cloudmall.user.service.impl.UserServiceImpl;
import com.xiaxinyu.cloudmall.zuul.feign.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 管理员鉴权过滤器
 */
@Component
public class AdminFilter extends ZuulFilter {

    @Autowired
    private UserFeignClient userFeignClient;
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String requestURI = request.getRequestURI();
        if(requestURI.contains("adminLogin")) {
            return false;
        }
        else return requestURI.contains("admin");
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(Constant.MALL_USER);
        if(user == null){
            ctx.setSendZuulResponse(false);
            ctx.setResponseBody("{    \"status\": 10010,    \"msg\": \"NEED_LOGIN\",    \"data\": null}");
            ctx.setResponseStatusCode(200);
            return null;
        }
        //校验是否是管理员
        Boolean adminRole = userFeignClient.checkAdminRole(user);
        if(! adminRole){
            ctx.setSendZuulResponse(false);
            ctx.setResponseBody("{    \"status\": 10011,    \"msg\": \"NEED_ADMIN\",    \"data\": null}");
            ctx.setResponseStatusCode(200);
        }
        return null;
    }
}
