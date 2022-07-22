package com.xiaxinyu.controller.management;

import com.xiaxinyu.entity.management.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/management")
public class ManagementController {

    @GetMapping("/index.html")
    public ModelAndView showIndex(HttpSession session){
        if(session.getAttribute("loginUser") == null) return new ModelAndView("redirect:/management/user/login");
        return new ModelAndView("/management/index");
    }

}
