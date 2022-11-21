package com.xiaxinyu.controller;

import com.xiaxinyu.domain.ResponseResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: xiaxinyu
 * @Email: xiaxinyuxy@163.com
 * @date: 2022年11月18日 18:02
 * @Copyright: 个人版权所有
 * @version: 1.0.0
 */

@RestController
public class HelloController {
    @RequestMapping("/hello")
//    @PreAuthorize("hasAuthority('system:dept:list1111')")
    @PreAuthorize("@ex.hasAuthority('system:dept:list')")
    public String hello(){
        return "hello";
    }

    @RequestMapping("/testCors")
    public ResponseResult testCore(){
        return new ResponseResult(200,"testCors");
    }
}