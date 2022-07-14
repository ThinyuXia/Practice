package com.xiaxinyu.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class FormController {
//    @PostMapping("/apply")
//    @ResponseBody
//    public String apply(@RequestParam(value = "n",defaultValue = "xxy") String name, String course, Integer[] purpose){
//        System.out.println(name);
//        System.out.println(course);
//        for(Integer p : purpose) System.out.println(p);
//        return "SUCCESS";
//    }


//    @PostMapping("/apply")
//    @ResponseBody
//    public String apply(@RequestParam(value = "n",defaultValue = "xxy") String name, String course,@RequestParam List<Integer> purpose){ //必须使用@RequestParam注解
//        System.out.println(name);
//        System.out.println(course);
//        for(Integer p : purpose) System.out.println(p);
//        return "SUCCESS";
//    }

//    @PostMapping("/apply")
//    @ResponseBody
//    public String apply(Form form){
//        return "SUCCESS";
//    }


    @PostMapping("/apply")
    @ResponseBody
    public String apply(@RequestParam Map map) { //map中的值不能接受复合数据，会造成数据丢失
        return "success";
    }
}
