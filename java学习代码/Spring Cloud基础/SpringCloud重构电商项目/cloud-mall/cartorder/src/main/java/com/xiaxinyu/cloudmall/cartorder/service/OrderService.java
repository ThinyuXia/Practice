package com.xiaxinyu.cloudmall.cartorder.service;

import com.github.pagehelper.PageInfo;
import com.xiaxinyu.cloudmall.cartorder.model.vo.OrderVO;
import com.xiaxinyu.cloudmall.cartorder.request.CreateOrderReg;

public interface OrderService {
    String create(CreateOrderReg createOrderReg);

    OrderVO detail(String orderNo);

    PageInfo listForCustomer(Integer pageNum, Integer pageSize);

    PageInfo listForAdmin(Integer pageNum, Integer pageSize);

    void cancel(String orderNo);

    String qrcode(String orderNo);

    void pay(String orderNo);

    void delivered(String orderNo);

    void finish(String orderNo);
}
