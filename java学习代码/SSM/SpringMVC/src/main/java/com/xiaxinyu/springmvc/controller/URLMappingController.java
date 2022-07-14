package com.xiaxinyu.springmvc.controller;

import com.xiaxinyu.springmvc.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/um")
public class URLMappingController {

    @GetMapping("/g")
    @ResponseBody
    public String getMapping(@RequestParam("manager_name") String managerName){
        System.out.println("managerName : " + managerName);
        return "This is get method";
    }

    @PostMapping("/p")
    @ResponseBody
    public String postMapping(String username,Long password){
        System.out.println(username + ":" + password);
        return "This is post method";
    }

    @PostMapping("/P1")
    @ResponseBody
    public String postMapping1(User user){
        System.out.println(user.getUsername() + " : " + user.getPassword());
        return "p1";
    }

    @GetMapping("/view")
    public ModelAndView showView(Integer userId){
//        ModelAndView mav = new ModelAndView("/view.jsp");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/view.jsp");

        User user = new User();
        if(userId == 1) user.setUsername("Lily");
        else user.setUsername("Smith");
        mav.addObject("u",user);
        return mav;
    }

//    String 与 ModelMap
//    Controller返回String的情况
//    1.方法被@ResponseBody描述，SpringMVC直接响应String字符串本身
//    2.方法不存在@ResponseBody，则SpringMVC处理Spring指代的视图(页面)
    @GetMapping("/view1")
    public String showView1(Integer userId, ModelMap modelMap){
        String view = "/view.jsp";
        User user = new User();
        if(userId == 1) user.setUsername("Lily");
        else user.setUsername("Smith");
        modelMap.addAttribute("u",user);
        return view;
    }
}
