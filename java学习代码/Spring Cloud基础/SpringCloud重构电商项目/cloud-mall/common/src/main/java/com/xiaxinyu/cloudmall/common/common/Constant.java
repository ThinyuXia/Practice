package com.xiaxinyu.cloudmall.common.common;

import com.google.common.collect.Sets;
import com.xiaxinyu.cloudmall.common.exception.ExceptionEnum;
import com.xiaxinyu.cloudmall.common.exception.ExceptionUnify;
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

    public enum OrderStatusEnum{
        CANCELED(0,"用户已取消"),
        NOT_PAY(10,"未付款"),
        PAID(20,"已付款"),
        DELIVERED(30,"已发货"),
        FINISHED(40,"交易完成"),
        ;


        private int code;
        private String name;

        OrderStatusEnum(int code, String name) {
            this.code = code;
            this.name = name;
        }

        public static  OrderStatusEnum codeOf(int code){
            for(OrderStatusEnum orderStatusEnum : values()){ //遍历所有枚举
                if(orderStatusEnum.getCode() == code)
                    return orderStatusEnum;
            }
            throw new ExceptionUnify(ExceptionEnum.NO_ENUM);
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
