package com.xiaxinyu.mall.controller;

import com.github.pagehelper.PageInfo;
import com.xiaxinyu.mall.common.ApiRestResponse;
import com.xiaxinyu.mall.common.Constant;
import com.xiaxinyu.mall.exception.ExceptionEnum;
import com.xiaxinyu.mall.model.pojo.Category;
import com.xiaxinyu.mall.model.pojo.User;
import com.xiaxinyu.mall.model.request.AddCategoryReq;
import com.xiaxinyu.mall.model.request.UpdateCategoryReq;
import com.xiaxinyu.mall.service.CategoryService;
import com.xiaxinyu.mall.service.UserService;
import com.xiaxinyu.mall.model.vo.CategoryVO;
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
    private UserService userService;

    @Resource
    private CategoryService categoryService;

    @ApiOperation("后台添加分类")
    @PostMapping("/admin/category/add")
    @ResponseBody
    public ApiRestResponse addCategory(HttpSession session, @Valid @RequestBody AddCategoryReq addCategoryReq){

//        if(addCategoryReq.getName() == null || addCategoryReq.getOrderNum() == null || addCategoryReq.getParentId() == null || addCategoryReq.getType() == null){
//            return ApiRestResponse.error(ExceptionEnum.PARA_NOT_NULL);
//        }


        User user = (User) session.getAttribute(Constant.MALL_USER);
        if(user == null){
            return ApiRestResponse.error(ExceptionEnum.NEED_LOGIN);
        }


        boolean isAdmin = userService.checkAdminRole(user);  //校验是否是管理员
        if(! isAdmin){
            return ApiRestResponse.error(ExceptionEnum.NEED_ADMIN);
        }else{
            categoryService.add(addCategoryReq);
            return ApiRestResponse.success();
        }
    }

    @ApiOperation("后台更新分类")
    @PostMapping("/admin/category/update")
    @ResponseBody
    public ApiRestResponse updateCategory(HttpSession session,@Valid @RequestBody UpdateCategoryReq updateCategoryReq){
        User user = (User) session.getAttribute(Constant.MALL_USER);
        if(user == null){
            return ApiRestResponse.error(ExceptionEnum.NEED_LOGIN);
        }


        boolean isAdmin = userService.checkAdminRole(user);  //校验是否是管理员
        if(! isAdmin){
            return ApiRestResponse.error(ExceptionEnum.NEED_ADMIN);
        }else{
            Category category = new Category();
            BeanUtils.copyProperties(updateCategoryReq,category);
            categoryService.update(category);
            return ApiRestResponse.success();
        }
    }

    @ApiOperation("后台删除分类")
    @PostMapping("/admin/category/delete")
    @ResponseBody
    public ApiRestResponse deleteCategory(@RequestParam Integer id){
        categoryService.delete(id);
        return ApiRestResponse.success();
    }

    @ApiOperation("后台分类列表")
    @GetMapping("/admin/category/list")
    @ResponseBody
    public ApiRestResponse listCategoryForAdmin(@RequestParam Integer pageNum,@RequestParam Integer pageSize){
        PageInfo pageInfo = categoryService.listForAdmin(pageNum,pageSize);
        return ApiRestResponse.success(pageInfo);
    }

    @ApiOperation("前台分类列表")
    @GetMapping("/category/list")
    @ResponseBody
    public ApiRestResponse listCategory(){

        List<CategoryVO> categoryVOS = categoryService.listForCustomer(0);
        return ApiRestResponse.success(categoryVOS);
    }



}
