package com.xiaxinyu.mall.controller;

import com.github.pagehelper.PageInfo;
import com.xiaxinyu.mall.common.ApiRestResponse;
import com.xiaxinyu.mall.model.pojo.Product;
import com.xiaxinyu.mall.model.request.ProductListReq;
import com.xiaxinyu.mall.service.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 前台商品Controller
 */
@RestController
public class ProductController {
    @Resource
    private ProductService productService;

    @GetMapping("/product/detail")
    public ApiRestResponse detail(@RequestParam Integer id){
        Product product = productService.detail(id);
        return ApiRestResponse.success(product);
    }

    @GetMapping("/product/list")
    public ApiRestResponse detail(ProductListReq productListReq){
        PageInfo pageInfo = productService.list(productListReq);
        return ApiRestResponse.success(pageInfo);
    }
}
