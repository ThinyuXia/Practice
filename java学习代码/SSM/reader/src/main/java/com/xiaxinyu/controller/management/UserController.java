package com.xiaxinyu.controller.management;

import com.xiaxinyu.entity.management.User;
import com.xiaxinyu.service.UserService;
import com.xiaxinyu.service.exception.BussinessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/management/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/check_login")
    @ResponseBody
    public Map login(String username, String password, HttpSession session){
        Map result = new HashMap();
        try{
            User user = userService.checkLogin(username, password);
            if(user != null){
                session.setAttribute("loginUser",user);
                result.put("code","0");
                result.put("msg","success");
            }
        }catch (BussinessException ex){
            ex.printStackTrace();
            result.put("code", ex.getCode());
            result.put("msg", ex.getMsg());
        }
        return result;
    }

    @GetMapping("/login")
    public ModelAndView showLogin(){
        return new ModelAndView("/management/login");
    }
}
