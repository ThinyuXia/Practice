package com.xiaxinyu.cloudmall.cartorder.feign;

import com.xiaxinyu.cloudmall.categoryproduct.model.pojo.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "cloudmall-categoryproduct")
public interface ProductFeignClient {
    @GetMapping("/product/detailForFeign")
    public Product detailForFeign(@RequestParam Integer id);

    @PostMapping("/product/updateStock")
    public void  updateStock(@RequestParam Integer productId,@RequestParam Integer stock);
}
