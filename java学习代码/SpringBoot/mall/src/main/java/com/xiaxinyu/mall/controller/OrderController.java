package com.xiaxinyu.mall.controller;

import com.github.pagehelper.PageInfo;
import com.xiaxinyu.mall.common.ApiRestResponse;
import com.xiaxinyu.mall.model.request.CreateOrderReg;
import com.xiaxinyu.mall.model.vo.OrderVO;
import com.xiaxinyu.mall.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 订单Controller
 */
@RestController
public class OrderController {
    @Resource
    private OrderService orderService;

    @PostMapping("/order/create")
    public ApiRestResponse create(@RequestBody CreateOrderReg createOrderReg){
        String orderNo = orderService.create(createOrderReg);
        return ApiRestResponse.success(orderNo);
    }

    @GetMapping("/order/detail")
    public ApiRestResponse detail(String orderNo){
        OrderVO orderVO = orderService.detail(orderNo);
        return ApiRestResponse.success(orderVO);
    }

    @GetMapping("/order/list")
    public ApiRestResponse list(Integer pageNum,Integer pageSize){
        PageInfo pageInfo = orderService.listForCustomer(pageNum,pageSize);
        return ApiRestResponse.success(pageInfo);
    }

    @PostMapping("/order/cancel")
    public ApiRestResponse list(String orderNo){
        orderService.cancel(orderNo);
        return ApiRestResponse.success();
    }

    @PostMapping("/order/qrcode")
    public ApiRestResponse qrcode(String orderNo){
        String pngAddress = orderService.qrcode(orderNo);
        return ApiRestResponse.success(pngAddress);
    }

    @GetMapping("/pay")
    public ApiRestResponse pay(String orderNo){
        orderService.pay(orderNo);
        return ApiRestResponse.success();
    }

}
