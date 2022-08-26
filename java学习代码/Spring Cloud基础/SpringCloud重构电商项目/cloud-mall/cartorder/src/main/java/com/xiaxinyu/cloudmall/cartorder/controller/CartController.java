package com.xiaxinyu.cloudmall.cartorder.controller;


import com.xiaxinyu.cloudmall.cartorder.feign.UserFeignClient;
import com.xiaxinyu.cloudmall.cartorder.model.vo.CartVO;
import com.xiaxinyu.cloudmall.cartorder.service.CartService;
import com.xiaxinyu.cloudmall.common.common.ApiRestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 购物车Controller
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    @Resource
    private CartService cartService;

    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping("list")
    public ApiRestResponse list(){
        //内部获取用户ID，防止横向越权
        List<CartVO> cartVOList = cartService.list(userFeignClient.getUser().getId());
        return ApiRestResponse.success(cartVOList);
    }


    @PostMapping("/add")
    public ApiRestResponse add(@RequestParam Integer productId,@RequestParam Integer count){
        List<CartVO> categoryVOList = cartService.add(userFeignClient.getUser().getId(),productId,count);
        return ApiRestResponse.success(categoryVOList);
    }

    @PostMapping("/update")
    public ApiRestResponse update(@RequestParam Integer productId,@RequestParam Integer count){
        List<CartVO> categoryVOList = cartService.update(userFeignClient.getUser().getId(),productId,count);
        return ApiRestResponse.success(categoryVOList);
    }

    @PostMapping("/delete")
    public ApiRestResponse delete(@RequestParam Integer productId){
        List<CartVO> categoryVOList = cartService.delete(userFeignClient.getUser().getId(),productId);
        return ApiRestResponse.success(categoryVOList);
    }

    @PostMapping("/select")
    public ApiRestResponse select(Integer productId,Integer selected){
        List<CartVO> cartVOList = cartService.selectOrNot(userFeignClient.getUser().getId(),productId,selected);
        return ApiRestResponse.success(cartVOList);
    }

    @PostMapping("/selectAll")
    public ApiRestResponse selectAll(Integer selected){
        List<CartVO> cartVOList = cartService.selectAllOrNot(userFeignClient.getUser().getId(),selected);
        return ApiRestResponse.success(cartVOList);
    }
}
