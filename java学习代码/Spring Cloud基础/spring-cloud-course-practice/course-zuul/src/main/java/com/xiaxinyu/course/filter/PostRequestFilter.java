package com.xiaxinyu.course.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * 计算处理请求时间
 */
@Component
public class PostRequestFilter extends ZuulFilter {
    @Override
    public String filterType() {
        //过滤器的类型
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        //是否启用过滤器
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        Long startTime = (Long)requestContext.get("startTime");
        long duration = System.currentTimeMillis() - startTime;
        String uri = requestContext.getRequest().getRequestURI();
        System.out.println("uri ：" + uri + ",处理时常 ：" + duration);
        return null;
    }
}
