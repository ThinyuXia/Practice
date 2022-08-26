package com.xiaxinyu.cloudmall.categoryproduct.controller;

import com.github.pagehelper.PageInfo;
import com.xiaxinyu.cloudmall.categoryproduct.model.pojo.Product;
import com.xiaxinyu.cloudmall.categoryproduct.model.request.ProductListReq;
import com.xiaxinyu.cloudmall.categoryproduct.service.ProductService;
import com.xiaxinyu.cloudmall.common.common.ApiRestResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/product/detailForFeign")
    public Product detailForFeign(@RequestParam Integer id){
        return productService.detail(id);
    }

    @PostMapping("/product/updateStock")
    public void  updateStock(@RequestParam Integer productId,@RequestParam Integer stock){
        productService.updateStock(productId,stock);
    }
}
