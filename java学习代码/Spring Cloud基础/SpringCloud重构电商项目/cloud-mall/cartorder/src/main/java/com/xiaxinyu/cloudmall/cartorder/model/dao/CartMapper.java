package com.xiaxinyu.cloudmall.cartorder.model.dao;


import com.xiaxinyu.cloudmall.cartorder.model.pojo.Cart;
import com.xiaxinyu.cloudmall.cartorder.model.vo.CartVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);

    List<CartVO> selectList(Integer userId);

    Cart selectCartByUserIdAndProductId(@Param("userId") Integer userId,@Param("productId") Integer productId);

    Integer selectOrNot(@Param("userId") Integer userId,@Param("productId") Integer productId,@Param("selected") Integer selected);
}