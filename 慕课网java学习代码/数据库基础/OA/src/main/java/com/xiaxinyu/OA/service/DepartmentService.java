package com.xiaxinyu.OA.service;

import com.xiaxinyu.OA.dao.DepartmentDao;
import com.xiaxinyu.OA.entity.Department;
import com.xiaxinyu.OA.utils.MybatisUtils;

public class DepartmentService {
    public Department selectById(Long departmentId){
        return (Department) MybatisUtils.executeQuery(sqlSession -> {
           DepartmentDao departmentDao = sqlSession.getMapper(DepartmentDao.class);
           return departmentDao.selectById(departmentId);
        });
    }
}
