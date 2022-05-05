package com.xiaxinyu.OA.dao;

import com.xiaxinyu.OA.entity.Department;
import com.xiaxinyu.OA.entity.Employee;

/**
 * 部门服务
 */
public interface DepartmentDao {
    /**
     * 根据员工编号查询员工
     * @param departmentId 员工编号
     * @return 部门对象，不存在时返回null
     */
    public Department selectById(Long departmentId);
}
