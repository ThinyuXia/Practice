package com.xiaxinyu.cloudmall.categoryproduct.controller;

import com.github.pagehelper.PageInfo;
import com.xiaxinyu.cloudmall.categoryproduct.common.ProductConstant;
import com.xiaxinyu.cloudmall.categoryproduct.model.pojo.Product;
import com.xiaxinyu.cloudmall.categoryproduct.model.request.AddProductReq;
import com.xiaxinyu.cloudmall.categoryproduct.model.request.UpdateProductReq;
import com.xiaxinyu.cloudmall.categoryproduct.service.ProductService;
import com.xiaxinyu.cloudmall.common.common.ApiRestResponse;

import com.xiaxinyu.cloudmall.common.exception.ExceptionEnum;
import com.xiaxinyu.cloudmall.common.exception.ExceptionUnify;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

/**
 * 后台商品管理Conroller
 */
@RestController
public class ProductAdminController {

    @Resource
    private ProductService productService;

    @Value("${file.upload.ip}")
    String ip;
    @Value("${file.upload.port}")
    String port;

    @PostMapping("/admin/product/add")
    public ApiRestResponse addProduct(@Valid @RequestBody AddProductReq addProductReq){
        productService.add(addProductReq);
        return ApiRestResponse.success();
    }

    @PostMapping("/admin/upload/file")
    public ApiRestResponse upload(HttpServletRequest request, @RequestParam("file")MultipartFile file){
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //生成文件名称UUID
        UUID uuid = UUID.randomUUID();
        String newFileName = uuid.toString() + suffixName;
        File fileDirectory = new File(ProductConstant.FILE_UPLOAD_DIR);
        File destFile = new File(ProductConstant.FILE_UPLOAD_DIR + newFileName);
        //判断文件夹是否存在
        if(! fileDirectory.exists()){
            if(! fileDirectory.mkdir()){
                throw new ExceptionUnify(ExceptionEnum.MKDIR_FAILED);
            }
        }
        try {
            file.transferTo(destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return ApiRestResponse.success(getHost(new URI(request.getRequestURL() + "")) + "/categoryproduct/images/" + newFileName);
        } catch (URISyntaxException e) {
            return ApiRestResponse.error(ExceptionEnum.UPLOAD_FAILED);
        }
    }


    @PostMapping("/admin/product/update")
    public ApiRestResponse updateProduct(@Valid @RequestBody UpdateProductReq updateProductReq){
        Product product = new Product();
        BeanUtils.copyProperties(updateProductReq,product);
        productService.update(product);
        return ApiRestResponse.success();
    }

    @PostMapping("/admin/product/delete")
    public ApiRestResponse deleteProduct(@RequestParam Integer id){
        productService.delete(id);
        return ApiRestResponse.success();
    }

    @PostMapping("/admin/product/batchUpdateSellStatus")
    public ApiRestResponse batchUpdateSellStatus(@RequestParam Integer[] ids,@RequestParam Integer sellStatus){
        productService.batchUpdateSellStatus(ids,sellStatus);
        return ApiRestResponse.success();
    }


    @GetMapping("/admin/product/list")
    public ApiRestResponse listForAdmin(@RequestParam Integer pageNum,@RequestParam Integer pageSize){
        PageInfo pageInfo = productService.listForAdmin(pageNum,pageSize);
        return ApiRestResponse.success(pageInfo);
    }

    private URI getHost(URI uri){
        URI effectiveURI;
        try {
            effectiveURI = new URI(uri.getScheme(), uri.getUserInfo(),ip,Integer.parseInt(port),null,null,null);
        } catch (URISyntaxException e) {
            effectiveURI = null;
        }
        return effectiveURI;
    }

}
