package com.xiaxinyu.cloudmall.cartorder.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.zxing.WriterException;
import com.xiaxinyu.cloudmall.cartorder.feign.ProductFeignClient;
import com.xiaxinyu.cloudmall.cartorder.feign.UserFeignClient;
import com.xiaxinyu.cloudmall.cartorder.model.dao.CartMapper;
import com.xiaxinyu.cloudmall.cartorder.model.dao.OrderItemMapper;
import com.xiaxinyu.cloudmall.cartorder.model.dao.OrderMapper;
import com.xiaxinyu.cloudmall.cartorder.model.pojo.Order;
import com.xiaxinyu.cloudmall.cartorder.model.pojo.OrderItem;
import com.xiaxinyu.cloudmall.cartorder.model.vo.CartVO;
import com.xiaxinyu.cloudmall.cartorder.model.vo.OrderItemVO;
import com.xiaxinyu.cloudmall.cartorder.model.vo.OrderVO;
import com.xiaxinyu.cloudmall.cartorder.request.CreateOrderReg;
import com.xiaxinyu.cloudmall.cartorder.service.CartService;
import com.xiaxinyu.cloudmall.cartorder.service.OrderService;
import com.xiaxinyu.cloudmall.cartorder.util.OrderCodeFactory;
import com.xiaxinyu.cloudmall.categoryproduct.common.ProductConstant;
import com.xiaxinyu.cloudmall.categoryproduct.model.pojo.Product;
import com.xiaxinyu.cloudmall.common.common.Constant;
import com.xiaxinyu.cloudmall.common.exception.ExceptionEnum;
import com.xiaxinyu.cloudmall.common.exception.ExceptionUnify;
import com.xiaxinyu.cloudmall.common.util.QRCodeGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Resource
    private CartService cartService;

    @Resource
    private ProductFeignClient productFeignClient;

    @Resource
    private CartMapper cartMapper;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderItemMapper orderItemMapper;

    @Resource
    private UserFeignClient userFeignClient;

    @Value("${file.upload.ip}")
    String ip;

    @Value("${file.upload.port}")
    String port;

    @Value("${file.upload.dir}")
    String FILE_UPLOAD_DIR;

    @Override
    //开启数据库事务
    @Transactional(rollbackFor = Exception.class)
    public String create(CreateOrderReg createOrderReg){
        //拿到用户ID
        Integer userId = userFeignClient.getUser().getId();
        //从购物车查找已经勾选的商品
        List<CartVO> cartVOList = cartService.list(userId);
        List<CartVO> cartVOListTemp = new ArrayList<>();
        for(CartVO cartVO : cartVOList){
            if(cartVO.getSelected().equals(Constant.Cart.CHECKED)){
                cartVOListTemp.add(cartVO);
            }
        }
        cartVOList = cartVOListTemp;
        //如果购物车已勾选商品为空，报错
        if(CollectionUtils.isEmpty(cartVOList)){
            throw new ExceptionUnify(ExceptionEnum.CART_EMPTY);
        }
        //判断商品是否存在，上下架状态，库存
        validSaleStatusAndStock(cartVOList);
        //把购物车对象转换为订单item对象
        List<OrderItem> orderItemList = cartVOListToOrderItemList(cartVOList);
        //扣库存
        for(OrderItem orderItem : orderItemList){
            Product product = productFeignClient.detailForFeign(orderItem.getProductId());
            int stock = product.getStock() - orderItem.getQuantity();
            if(stock < 0) throw new ExceptionUnify(ExceptionEnum.NOT_ENOUGH);
            product.setStock(stock);
            productFeignClient.updateStock(product.getId(),stock);
        }
        //把购物车中的已勾选商品删除
        cleanCart(cartVOList);
        //生成订单
        Order order = new Order();
        //按照规则生成订单号
        String orderNo = OrderCodeFactory.getOrderCode(Long.valueOf(userId));
        order.setOrderNo(orderNo);
        order.setUserId(userId);
        order.setTotalPrice(totalPrice(orderItemList));
        order.setReceiverName(createOrderReg.getReceiverName());
        order.setReceiverMobile(createOrderReg.getReceiverMobile());
        order.setReceiverAddress(createOrderReg.getReceiverAddress());
        order.setOrderStatus(Constant.OrderStatusEnum.NOT_PAY.getCode());
        order.setPostage(0);
        order.setPaymentType(1);
        //插入到order表中
        orderMapper.insertSelective(order);
        //循环保存每个商品保存到order_item表
        for(OrderItem orderItem : orderItemList){
            orderItem.setOrderNo(orderNo);
            orderItemMapper.insertSelective(orderItem);
        }
        //把结果返回
        return orderNo;
    }

    @Override
    public OrderVO detail(String orderNo){
        Order order = orderMapper.selectByOrderNo(orderNo);
        if(order == null){ //订单不存在
            throw new ExceptionUnify(ExceptionEnum.NO_ORDER);
        }
        //订单存在，判断订单所属,验证用户身份
        Integer userId = userFeignClient.getUser().getId();
        if(! order.getUserId().equals(userId)){
            throw new ExceptionUnify(ExceptionEnum.NO_YOUR_ORDER);
        }
        return getOrderVO(order);
    }

    @Override
    public PageInfo listForCustomer(Integer pageNum, Integer pageSize){
        Integer userId = userFeignClient.getUser().getId();
        PageHelper.startPage(pageNum,pageSize);
        List<Order> orderList = orderMapper.selectForCustomer(userId);
        List<OrderVO> orderVOList = orderListToOrderVOList(orderList);
        PageInfo pageInfo = new PageInfo<>(orderVOList);
        return pageInfo;
    }

    @Override
    public PageInfo listForAdmin(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Order> orderList = orderMapper.selectAllForAdmin();
        List<OrderVO> orderVOList = orderListToOrderVOList(orderList);
        PageInfo pageInfo = new PageInfo<>(orderVOList);
        return pageInfo;
    }

    @Override
    public void cancel(String orderNo){
        Order order = orderMapper.selectByOrderNo(orderNo);
        if(order == null){
            throw new ExceptionUnify(ExceptionEnum.NO_ORDER);
        }
        ////订单存在，判断订单所属,验证用户身份
        Integer userId = userFeignClient.getUser().getId();
        if(! order.getUserId().equals(userId)){
            throw new ExceptionUnify(ExceptionEnum.NO_YOUR_ORDER);
        }
        if(order.getOrderStatus().equals(Constant.OrderStatusEnum.NOT_PAY.getCode())){
            order.setOrderStatus(Constant.OrderStatusEnum.CANCELED.getCode());
            order.setEndTime(new Date());
            orderMapper.updateByPrimaryKeySelective(order);
        }else{
            throw new ExceptionUnify(ExceptionEnum.WRONG_ORDER_STATUS);
        }
    }

    @Override
    public String qrcode(String orderNo){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

//        try {
//            //获取局域网ip，这种方法获取的值和电脑的配置文件有关，可能是不准确的
//            ip = InetAddress.getLocalHost().getHostAddress();
//            System.out.println(ip);
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }
        String address = ip + ":" +  port;
        String payUrl = "http://" + address + "/cartorder/pay?orderNo=" + orderNo;
        try {
            QRCodeGenerator.generateQRCodeImage(payUrl,350,350, FILE_UPLOAD_DIR + orderNo + ".png");
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String pngAddress = "http://" + address + "/cartorder/images/" + orderNo + ".png";
        return pngAddress;
    }

    @Override
    public void pay(String orderNo){
        Order order = orderMapper.selectByOrderNo(orderNo);
        if(order == null){
            throw new ExceptionUnify(ExceptionEnum.NO_ORDER);
        }
        if(order.getOrderStatus() == Constant.OrderStatusEnum.NOT_PAY.getCode()){
            order.setOrderStatus(Constant.OrderStatusEnum.PAID.getCode());
            order.setPayTime(new Date());
            orderMapper.updateByPrimaryKeySelective(order);
        }else{
            throw new ExceptionUnify(ExceptionEnum.WRONG_ORDER_STATUS);
        }
    }

    @Override
    public void delivered(String orderNo){
        Order order = orderMapper.selectByOrderNo(orderNo);
        if(order == null){
            throw new ExceptionUnify(ExceptionEnum.NO_ORDER);
        }
        if(order.getOrderStatus() == Constant.OrderStatusEnum.PAID.getCode()){
            order.setOrderStatus(Constant.OrderStatusEnum.DELIVERED.getCode());
            order.setDeliveryTime(new Date());
            orderMapper.updateByPrimaryKeySelective(order);
        }else{
            throw new ExceptionUnify(ExceptionEnum.WRONG_ORDER_STATUS);
        }
    }

    @Override
    public void finish(String orderNo){
        Order order = orderMapper.selectByOrderNo(orderNo);
        if(order == null){
            throw new ExceptionUnify(ExceptionEnum.NO_ORDER);
        }
        //如果是普通用户，就要校验订单的所属
        if(userFeignClient.getUser().getRole().equals(1) && ! order.getUserId().equals(userFeignClient.getUser().getId())){
            throw new ExceptionUnify(ExceptionEnum.NO_YOUR_ORDER);
        }

        //发货后完结订单
        if(order.getOrderStatus() == Constant.OrderStatusEnum.DELIVERED.getCode()){
            order.setOrderStatus(Constant.OrderStatusEnum.FINISHED.getCode());
            order.setEndTime(new Date());
            orderMapper.updateByPrimaryKeySelective(order);
        }else{
            throw new ExceptionUnify(ExceptionEnum.WRONG_ORDER_STATUS);
        }
    }


    private List<OrderVO> orderListToOrderVOList(List<Order> orderList) {
        List<OrderVO> orderVOList = new ArrayList<>();
        for(Order order : orderList){
            orderVOList.add(getOrderVO(order));
        }
        return orderVOList;
    }

    private OrderVO getOrderVO(Order order) {
        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(order,orderVO);
        //获取订单对应的orderItemList
        List<OrderItem> orderItemList = orderItemMapper.selectByOrderNo(order.getOrderNo());
        List<OrderItemVO> orderItemVOList = new ArrayList<>();
        for(OrderItem orderItem : orderItemList){
            OrderItemVO orderItemVO = new OrderItemVO();
            BeanUtils.copyProperties(orderItem,orderItemVO);
            orderItemVOList.add(orderItemVO);
        }
        orderVO.setOrderItemVOList(orderItemVOList);
        orderVO.setOrderStatusName(Constant.OrderStatusEnum.codeOf(orderVO.getOrderStatus()).getName());
        return orderVO;
    }

    private Integer totalPrice(List<OrderItem> orderItemList) {
        Integer totalPrice = 0;
        for(OrderItem orderItem : orderItemList) totalPrice += orderItem.getTotalPrice();
        return totalPrice;
    }

    private void cleanCart(List<CartVO> cartVOList) {
        for(CartVO cartVO : cartVOList){
            cartMapper.deleteByPrimaryKey(cartVO.getId());
        }
    }

    private List<OrderItem> cartVOListToOrderItemList(List<CartVO> cartVOList) {
        List<OrderItem> orderItemList = new ArrayList<>();
        for(CartVO cartVO : cartVOList){
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(cartVO.getProductId());
            //记录商品快照信息
            orderItem.setProductName(cartVO.getProductName());
            orderItem.setProductImg(cartVO.getProductImage());
            orderItem.setUnitPrice(cartVO.getPrice());
            orderItem.setQuantity(cartVO.getQuantity());
            orderItem.setTotalPrice(cartVO.getTotalPrice());
            orderItemList.add(orderItem);
        }
        return orderItemList;
    }

    private void validSaleStatusAndStock(List<CartVO> cartVOList) {

        for(CartVO cartVO : cartVOList) {
            Product product = productFeignClient.detailForFeign(cartVO.getProductId());
            //判断商品是否存在并且为上架状态
            if (product == null || product.getStatus() != Constant.SaleStatus.SALE) {
                throw new ExceptionUnify(ExceptionEnum.NOT_SALE);
            }

            //判断商品库存
            if (cartVO.getQuantity() > product.getStock()) {
                throw new ExceptionUnify(ExceptionEnum.NOT_ENOUGH);
            }
        }

    }
}
