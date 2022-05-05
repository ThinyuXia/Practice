package com.xiaxinyu.OA.service;

import com.xiaxinyu.OA.dao.EmployeeDao;
import com.xiaxinyu.OA.entity.Employee;
import com.xiaxinyu.OA.utils.MybatisUtils;

public class EmployeeService {
    public Employee selectById(Long employeeId){
        return (Employee) MybatisUtils.executeQuery(sqlSession -> {
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            return  employeeDao.selectById(employeeId);
        });
    }
}
