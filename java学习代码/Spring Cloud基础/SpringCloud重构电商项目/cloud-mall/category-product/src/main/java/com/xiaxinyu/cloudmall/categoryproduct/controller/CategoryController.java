package com.xiaxinyu.cloudmall.categoryproduct.controller;

import com.github.pagehelper.PageInfo;
import com.xiaxinyu.cloudmall.categoryproduct.model.pojo.Category;
import com.xiaxinyu.cloudmall.categoryproduct.model.request.AddCategoryReq;
import com.xiaxinyu.cloudmall.categoryproduct.model.request.UpdateCategoryReq;
import com.xiaxinyu.cloudmall.categoryproduct.model.vo.CategoryVO;
import com.xiaxinyu.cloudmall.categoryproduct.service.CategoryService;
import com.xiaxinyu.cloudmall.common.common.ApiRestResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * 目录Controller
 */
@Controller
public class CategoryController {


    @Resource
    private CategoryService categoryService;

    @ApiOperation("后台添加分类")
    @PostMapping("/admin/category/add")
    @ResponseBody
    public ApiRestResponse addCategory(HttpSession session, @Valid @RequestBody AddCategoryReq addCategoryReq) {

//        if(addCategoryReq.getName() == null || addCategoryReq.getOrderNum() == null || addCategoryReq.getParentId() == null || addCategoryReq.getType() == null){
//            return ApiRestResponse.error(ExceptionEnum.PARA_NOT_NULL);
//        }


        categoryService.add(addCategoryReq);
        return ApiRestResponse.success();

    }

    @ApiOperation("后台更新分类")
    @PostMapping("/admin/category/update")
    @ResponseBody
    public ApiRestResponse updateCategory(HttpSession session, @Valid @RequestBody UpdateCategoryReq updateCategoryReq) {
        Category category = new Category();
        BeanUtils.copyProperties(updateCategoryReq,category);
        categoryService.update(category);
        return ApiRestResponse.success();

    }

    @ApiOperation("后台删除分类")
    @PostMapping("/admin/category/delete")
    @ResponseBody
    public ApiRestResponse deleteCategory(@RequestParam Integer id) {
        categoryService.delete(id);
        return ApiRestResponse.success();
    }

    @ApiOperation("后台分类列表")
    @GetMapping("/admin/category/list")
    @ResponseBody
    public ApiRestResponse listCategoryForAdmin(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        PageInfo pageInfo = categoryService.listForAdmin(pageNum, pageSize);
        return ApiRestResponse.success(pageInfo);
    }

    @ApiOperation("前台分类列表")
    @GetMapping("/category/list")
    @ResponseBody
    public ApiRestResponse listCategory() {

        List<CategoryVO> categoryVOS = categoryService.listForCustomer(0);
        return ApiRestResponse.success(categoryVOS);
    }


}
