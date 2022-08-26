package com.xiaxinyu.cloudmall.cartorder.feign;

import com.xiaxinyu.cloudmall.user.model.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("cloudmall-user")
public interface UserFeignClient {
    @GetMapping("/getUser")
    User getUser();
}
