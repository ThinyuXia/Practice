package com.xiaxinyu.spring.service;

import com.xiaxinyu.spring.ioc.dao.IUserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DepartmentService {
    /**
     * 1. @Resource设置name属性，则按name在IoC容器中将bean注入，如果未找到bean则报错
     * 2. @Resource未设置name属性
     * 2.1 以属性名作为bean name 在IoC容器中匹配bean，如有匹配则注入
     * 2.2 按属性名未匹配成功，则按类型进行匹配，同@Autowired，需加入@Primary解决类型冲突
     * 使用建议：在使用@Resource对象时推荐设置name或保证属性名与bean name 一致
     */
    @Resource
    private IUserDao uDao;

}
