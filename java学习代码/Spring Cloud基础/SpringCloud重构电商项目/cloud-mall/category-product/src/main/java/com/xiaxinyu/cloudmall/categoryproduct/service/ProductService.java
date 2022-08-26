package com.xiaxinyu.cloudmall.categoryproduct.service;

import com.github.pagehelper.PageInfo;
import com.xiaxinyu.cloudmall.categoryproduct.model.pojo.Product;
import com.xiaxinyu.cloudmall.categoryproduct.model.request.AddProductReq;
import com.xiaxinyu.cloudmall.categoryproduct.model.request.ProductListReq;

/**
 * 商品Service
 */
public interface ProductService {
    void add(AddProductReq addProductReq);

    void update(Product product);

    void delete(Integer id);

    void batchUpdateSellStatus(Integer[] ids, Integer sellStatus);

    PageInfo listForAdmin(Integer pageNum, Integer pageSize);

    Product detail(Integer id);

    PageInfo list(ProductListReq productListReq);

    void updateStock(Integer productId, Integer stock);
}
