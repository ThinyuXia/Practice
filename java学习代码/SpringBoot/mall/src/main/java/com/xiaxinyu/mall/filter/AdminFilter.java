package com.xiaxinyu.mall.filter;

import com.xiaxinyu.mall.common.ApiRestResponse;
import com.xiaxinyu.mall.common.Constant;
import com.xiaxinyu.mall.exception.ExceptionEnum;
import com.xiaxinyu.mall.model.pojo.Category;
import com.xiaxinyu.mall.model.pojo.User;
import com.xiaxinyu.mall.service.UserService;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 管理员校验过滤器
 */
public class AdminFilter implements Filter {

    @Resource
    private UserService userService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constant.MALL_USER);
        if(user == null){
            PrintWriter out = new HttpServletResponseWrapper((HttpServletResponse) servletResponse).getWriter();
            out.println("{    \"status\": 10007,    \"msg\": \"NEED_LOGIN\",    \"data\": null}");
            out.flush();
            out.close();
            return;
        }


        boolean isAdmin = userService.checkAdminRole(user);  //校验是否是管理员
        if(! isAdmin){
            PrintWriter out = new HttpServletResponseWrapper((HttpServletResponse) servletResponse).getWriter();
            out.println("{    \"status\": 10009,     \"msg\": \"NEED_ADMIN\",    \"data\": null}");
            out.flush();
            out.close();
        }else{
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
