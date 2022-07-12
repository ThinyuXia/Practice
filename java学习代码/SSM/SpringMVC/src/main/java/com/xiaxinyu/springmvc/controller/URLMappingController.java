package com.xiaxinyu.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/um")
public class URLMappingController {

    @GetMapping("/g")
    @ResponseBody
    public String getMapping(){
        return "This is get method";
    }

    @PostMapping("/p")
    @ResponseBody
    public String postMapping(){
        return "This is post method";
    }

}
