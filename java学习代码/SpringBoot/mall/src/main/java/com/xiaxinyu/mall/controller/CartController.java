package com.xiaxinyu.mall.controller;

import com.xiaxinyu.mall.common.ApiRestResponse;
import com.xiaxinyu.mall.filter.UserFilter;
import com.xiaxinyu.mall.service.CartService;
import com.xiaxinyu.mall.model.vo.CartVO;
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

    @GetMapping("list")
    public ApiRestResponse list(){
        //内部获取用户ID，防止横向越权
        List<CartVO> cartVOList = cartService.list(UserFilter.user.getId());
        return ApiRestResponse.success(cartVOList);
    }


    @PostMapping("/add")
    public ApiRestResponse add(@RequestParam Integer productId,@RequestParam Integer count){
        List<CartVO> categoryVOList = cartService.add(UserFilter.user.getId(),productId,count);
        return ApiRestResponse.success(categoryVOList);
    }

    @PostMapping("/update")
    public ApiRestResponse update(@RequestParam Integer productId,@RequestParam Integer count){
        List<CartVO> categoryVOList = cartService.update(UserFilter.user.getId(),productId,count);
        return ApiRestResponse.success(categoryVOList);
    }

    @PostMapping("/delete")
    public ApiRestResponse delete(@RequestParam Integer productId){
        List<CartVO> categoryVOList = cartService.delete(UserFilter.user.getId(),productId);
        return ApiRestResponse.success(categoryVOList);
    }

    @PostMapping("/select")
    public ApiRestResponse select(Integer productId,Integer selected){
        List<CartVO> cartVOList = cartService.selectOrNot(UserFilter.user.getId(),productId,selected);
        return ApiRestResponse.success(cartVOList);
    }

    @PostMapping("/selectAll")
    public ApiRestResponse selectAll(Integer selected){
        List<CartVO> cartVOList = cartService.selectAllOrNot(UserFilter.user.getId(),selected);
        return ApiRestResponse.success(cartVOList);
    }
}
