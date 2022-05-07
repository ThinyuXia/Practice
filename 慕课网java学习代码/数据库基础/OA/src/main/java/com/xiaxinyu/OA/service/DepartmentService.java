package com.xiaxinyu.OA.service;

import com.xiaxinyu.OA.dao.DepartmentDao;
import com.xiaxinyu.OA.entity.Department;
import com.xiaxinyu.OA.utils.MybatisUtils;

public class DepartmentService {

    /**
     * 按编号得到部门对象
     * @param departmentId 部门编号
     * @return 部门对象,null代表部门不存在
     */
    public Department selectById(Long departmentId){
        return (Department) MybatisUtils.executeQuery(
                sqlSession -> sqlSession.getMapper(DepartmentDao.class).selectById(departmentId)
        );
    }
}
