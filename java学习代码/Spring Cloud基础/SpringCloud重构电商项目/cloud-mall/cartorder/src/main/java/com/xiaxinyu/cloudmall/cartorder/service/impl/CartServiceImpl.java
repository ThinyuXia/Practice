package com.xiaxinyu.cloudmall.cartorder.service.impl;

import com.xiaxinyu.cloudmall.cartorder.feign.ProductFeignClient;
import com.xiaxinyu.cloudmall.cartorder.model.dao.CartMapper;
import com.xiaxinyu.cloudmall.cartorder.model.pojo.Cart;
import com.xiaxinyu.cloudmall.cartorder.model.vo.CartVO;
import com.xiaxinyu.cloudmall.cartorder.service.CartService;
import com.xiaxinyu.cloudmall.categoryproduct.model.pojo.Product;
import com.xiaxinyu.cloudmall.common.common.Constant;
import com.xiaxinyu.cloudmall.common.exception.ExceptionEnum;
import com.xiaxinyu.cloudmall.common.exception.ExceptionUnify;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 购物车Service实现类
 */
@Service("cartService")
public class CartServiceImpl implements CartService {
    @Resource
    private ProductFeignClient productFeignClient;

    @Resource
    private CartMapper cartMapper;

    @Override
    public List<CartVO> list(Integer userId){
        List<CartVO> cartVOList = cartMapper.selectList(userId);
        for(int i = 0;i < cartVOList.size();i ++){
            CartVO cartVO = cartVOList.get(i);
            cartVO.setTotalPrice(cartVO.getPrice() * cartVO.getQuantity());
        }
        return cartVOList;
    }

    @Override
    public List<CartVO> add(Integer userId, Integer productId, Integer count){
        //判断该商品是否可以添加进购物车
        validProduct(productId,count);

        Cart cart = cartMapper.selectCartByUserIdAndProductId(userId,productId);

        if(cart == null){  //说明该商品之前不在购物车中，需要新增一条记录
            cart = new Cart();
            cart.setProductId(productId);
            cart.setUserId(userId);
            cart.setQuantity(count);
            cart.setSelected(Constant.Cart.CHECKED);
            cartMapper.insertSelective(cart);
        }else{ //商品已经在购物车中，则数量相加
            count += cart.getQuantity();
            Cart nCart = new Cart();
            nCart.setId(cart.getId());
            nCart.setQuantity(count);
            nCart.setUserId(userId);
            nCart.setProductId(productId);
            nCart.setSelected(Constant.Cart.CHECKED);
            cartMapper.updateByPrimaryKeySelective(nCart);
        }

        return this.list(userId);

    }


    @Override
    public List<CartVO> update(Integer userId, Integer productId, Integer count){
        //判断该商品是否可以添加进购物车
        validProduct(productId,count);

        Cart cart = cartMapper.selectCartByUserIdAndProductId(userId,productId);

        if(cart == null){  //说明该商品之前不在购物车中，无法更新
            throw new ExceptionUnify(ExceptionEnum.UPDATE_FAILED);
        }else{ //商品已经在购物车中，则更新数量
            Cart nCart = new Cart();
            nCart.setId(cart.getId());
            nCart.setQuantity(count);
            nCart.setUserId(userId);
            nCart.setProductId(productId);
            nCart.setSelected(Constant.Cart.CHECKED);
            cartMapper.updateByPrimaryKeySelective(nCart);
        }
        return this.list(userId);
    }

    @Override
    public List<CartVO> delete(Integer userId, Integer productId){

        Cart cart = cartMapper.selectCartByUserIdAndProductId(userId,productId);

        if(cart == null){  //说明该商品之前不在购物车中，无法删除
            throw new ExceptionUnify(ExceptionEnum.DELETE_FAILED);
        }else{ //商品已经在购物车中，则删除
            cartMapper.deleteByPrimaryKey(cart.getId());
        }
        return this.list(userId);
    }

    public void validProduct(Integer productId,Integer count){
        Product product = productFeignClient.detailForFeign(productId);
        //判断商品是否存在并且为上架状态
        if(product == null || product.getStatus() != Constant.SaleStatus.SALE){
            throw new ExceptionUnify(ExceptionEnum.NOT_SALE);
        }

        //判断商品库存
        if(count > product.getStock()){
            throw new ExceptionUnify(ExceptionEnum.NOT_ENOUGH);
        }

    }

    @Override
    public List<CartVO> selectOrNot(Integer userId, Integer productId, Integer selected){
        Cart cart = cartMapper.selectCartByUserIdAndProductId(userId,productId);
        if(cart == null){
            throw new ExceptionUnify(ExceptionEnum.UPDATE_FAILED);
        }else{
            cartMapper.selectOrNot(userId,productId,selected);
        }
        return this.list(userId);
    }

    @Override
    public List<CartVO> selectAllOrNot(Integer userId,Integer selected){
        cartMapper.selectOrNot(userId,null,selected);
        return this.list(userId);
    }


}
