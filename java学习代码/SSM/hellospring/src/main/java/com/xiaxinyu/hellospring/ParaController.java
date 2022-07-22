package com.xiaxinyu.hellospring;

import org.springframework.web.bind.annotation.*;

/**
 * 演示各种传参形式
 */

@RestController
@RequestMapping("/prefix")
public class ParaController {
    @GetMapping({"/firstrequest"})
    public String firstRequest(){
        return "第一个SpringBoot接口";
    }

    @GetMapping({"/requestpara"})
    public String requestPara(@RequestParam Integer num){
        return "para from request : " + num;
    }

    @GetMapping({"/pathpara/{num}",})
    public String pathPara(@PathVariable("num") Integer n){
        return "para from request : " + n;
    }

    @GetMapping({"/multiurl1","/multiurl2"})
    public String multiUrl(Integer num){
        return "para from request : " + num;
    }

    @GetMapping({"/required"})
    public String required(@RequestParam(required = false,defaultValue = "0") Integer num){
        return "para from request : " + num;
    }
}
