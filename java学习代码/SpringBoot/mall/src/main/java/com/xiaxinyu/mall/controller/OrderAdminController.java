package com.xiaxinyu.mall.controller;

import com.github.pagehelper.PageInfo;
import com.xiaxinyu.mall.common.ApiRestResponse;
import com.xiaxinyu.mall.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 订单后台管理Controller
 */
@RestController
public class OrderAdminController {

    @Resource
    private OrderService orderService;

    @GetMapping("/admin/order/list")
    public ApiRestResponse listForAdmin(Integer pageNum,Integer pageSize){
        PageInfo pageInfo = orderService.listForAdmin(pageNum,pageSize);
        return ApiRestResponse.success(pageInfo);
    }

    @PostMapping("/admin/order/delivered")
    public ApiRestResponse delivered(String orderNo){
        orderService.delivered(orderNo);
        return ApiRestResponse.success();
    }

    @PostMapping("/order/finish")
    public ApiRestResponse finish(String orderNo){
        orderService.finish(orderNo);
        return ApiRestResponse.success();
    }

}
