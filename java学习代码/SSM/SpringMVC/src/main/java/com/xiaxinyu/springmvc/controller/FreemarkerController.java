package com.xiaxinyu.springmvc.controller;

import com.xiaxinyu.springmvc.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/fm")
public class FreemarkerController {
    @GetMapping("/test")
    public ModelAndView showTest(){
        ModelAndView mav = new ModelAndView("/test"); //这里的跟路径指向 /WEB-INF/ftl,已经配置好视图名后缀为.ftl
        User user = new User();
        user.setUsername("Andy");
        mav.addObject("u",user);
        return mav;
    }
}
