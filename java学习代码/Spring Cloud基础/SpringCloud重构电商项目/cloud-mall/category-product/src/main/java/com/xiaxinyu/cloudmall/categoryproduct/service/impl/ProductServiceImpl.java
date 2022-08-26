package com.xiaxinyu.cloudmall.categoryproduct.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaxinyu.cloudmall.categoryproduct.model.dao.ProductMapper;
import com.xiaxinyu.cloudmall.categoryproduct.model.pojo.Product;
import com.xiaxinyu.cloudmall.categoryproduct.model.request.AddProductReq;
import com.xiaxinyu.cloudmall.categoryproduct.model.request.ProductListReq;
import com.xiaxinyu.cloudmall.categoryproduct.model.vo.CategoryVO;
import com.xiaxinyu.cloudmall.categoryproduct.query.ProductListQuery;
import com.xiaxinyu.cloudmall.categoryproduct.service.CategoryService;
import com.xiaxinyu.cloudmall.categoryproduct.service.ProductService;
import com.xiaxinyu.cloudmall.common.common.Constant;
import com.xiaxinyu.cloudmall.common.exception.ExceptionEnum;
import com.xiaxinyu.cloudmall.common.exception.ExceptionUnify;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductMapper productMapper;

    @Resource
    private CategoryService categoryService;

    @Override
    public void add(AddProductReq addProductReq){
        Product product = new Product();
        BeanUtils.copyProperties(addProductReq,product);
        Product oldProduct = productMapper.selectByName(addProductReq.getName());
        if(oldProduct != null){
            throw new ExceptionUnify(ExceptionEnum.NAME_EXISTED);
        }
        int count = productMapper.insertSelective(product);
        if(count != 1){
            throw new ExceptionUnify(ExceptionEnum.CREATE_FAILED);
        }
    }

    @Override
    public void update(Product product){
        Product oldProduct = productMapper.selectByName(product.getName());
        if(oldProduct != null && !oldProduct.getId().equals(product.getId())){
            throw new ExceptionUnify(ExceptionEnum.NAME_EXISTED);
        }

        int count = productMapper.updateByPrimaryKeySelective(product);
        if(count != 1){
            throw new ExceptionUnify(ExceptionEnum.UPDATE_FAILED);
        }
    }

    @Override
    public void delete(Integer id){
        Product oldProduct = productMapper.selectByPrimaryKey(id);
        if(oldProduct == null){
            throw new ExceptionUnify(ExceptionEnum.DELETE_FAILED);
        }

        int count = productMapper.deleteByPrimaryKey(id);
        if(count != 1){
            throw new ExceptionUnify(ExceptionEnum.DELETE_FAILED);
        }
    }

    @Override
    public void batchUpdateSellStatus(Integer[] ids, Integer sellStatus){
        productMapper.batchUpdateSellStatus(ids, sellStatus);
    }

    @Override
    public PageInfo listForAdmin(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Product> productList = productMapper.selectListForAdmin();
        PageInfo pageInfo = new PageInfo(productList);
        return pageInfo;
    }

    @Override
    public Product detail(Integer id){
        Product product = productMapper.selectByPrimaryKey(id);
        if(product == null) throw new ExceptionUnify(ExceptionEnum.PRODUCT_NOT_EXISTS);
        return product;
    }

    @Override
    public PageInfo list(ProductListReq productListReq) {
        //构建Query对象
        ProductListQuery productListQuery = new ProductListQuery();
        //搜索条件处理
        if (productListReq.getKeyword() != null && !productListReq.equals("")) {
            String keyWord = new StringBuilder().append("%").append(productListReq.getKeyword()).append("%").toString();
            productListQuery.setKeyword(keyWord);
        }

        if(productListReq.getCategoryId() != null){
            List<CategoryVO> categoryVOList = categoryService.listForCustomer(productListReq.getCategoryId());
            List<Integer> categoryIds = new ArrayList<>();
            categoryIds.add(productListReq.getCategoryId());
            getCategoryIds(categoryVOList,categoryIds);
            productListQuery.setCategoryIds(categoryIds);
        }

        //排序处理
        String orderBy = productListReq.getOrderBy();
        if(Constant.ProductListOrderBy.PRICE_ASC_DESC.contains(orderBy)){
            PageHelper.startPage(productListReq.getPageNum(),productListReq.getPageSize(),orderBy);
        }else{
            PageHelper.startPage(productListReq.getPageNum(),productListReq.getPageSize());
        }

        List<Product> productList = productMapper.selectList(productListQuery);
        return new PageInfo(productList);
    }

    private void getCategoryIds(List<CategoryVO> categoryVOList,List<Integer> categoryIds){
        for(int i = 0;i < categoryVOList.size();i ++){
            CategoryVO categoryVO = categoryVOList.get(i);
            if(categoryVO != null) categoryIds.add(categoryVO.getId());
            getCategoryIds(categoryVO.getChildCategory(),categoryIds);
        }
    }

    @Override
    public void updateStock(Integer productId,Integer stock){
        Product product = new Product();
        product.setId(productId);
        product.setStock(stock);
        productMapper.updateByPrimaryKeySelective(product);
    }
}
