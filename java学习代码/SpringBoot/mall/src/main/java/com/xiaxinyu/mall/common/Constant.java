package com.xiaxinyu.mall.common;

import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 常量值
 */
@Component
public class Constant {
    public static final String SALT = "6chnjkxjkjcD[p;s'ijioIK";
    public static final String MALL_USER = "LOGIN_USER";

    public static String FILE_UPLOAD_DIR; //静态属性需要使用@Value配合set方法完成值的注入

    @Value("${file.upload.dir}")
    public void setFileUploadDir(String fileUploadDir){
        FILE_UPLOAD_DIR = fileUploadDir;
    }

    public interface  ProductListOrderBy{
        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price desc","price asc");
    }

    public interface SaleStatus{
        int NOT_SALE = 0; //商品下架状态
        int SALE = 1; //商品上架状态
    }

    public interface Cart{
        int UN_CHECKED = 0; //购物车未选中商品
        int CHECKED = 1; //购物车选中商品
    }
}
