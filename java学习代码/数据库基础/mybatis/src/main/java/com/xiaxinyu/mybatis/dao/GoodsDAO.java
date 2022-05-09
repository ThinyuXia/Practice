package com.xiaxinyu.mybatis.dao;

import com.xiaxinyu.mybatis.entity.Goods;
import org.apache.ibatis.annotations.*;

import java.sql.Statement;
import java.util.List;

public interface GoodsDAO {

    @Select("SELECT * FROM t_goods WHERE current_price BETWEEN #{min} AND #{max} LIMIT 0,#{limit}")
    public List<Goods> selectByPriceRange(@Param("min") Float min,@Param("max") Float max,@Param("limit") Integer limit);

    @Insert("INSERT INTO t_goods (title,sub_title,original_cost,current_price,discount,is_free_delivery,category_id)\n" +
            "            VALUES(#{title},#{subTitle},#{originalCost},#{currentPrice},#{discount},#{isFreeDelivery},#{categoryId})")
    @SelectKey(statement = "SELECT last_insert_id()",before = false,keyProperty = "goodsId",resultType = Integer.class)
    public int insert(Goods goods);

    @Select("SELECT * FROM t_goods")
    @Results({ //<resultMap>
            @Result(column = "goods_id",property = "goodsId",id = true), //<id>
            @Result(column = "title",property = "title"), //<result>
            @Result(column = "current_price",property = "currentPrice")
    })
    public List<Goods> selectAll();
}
