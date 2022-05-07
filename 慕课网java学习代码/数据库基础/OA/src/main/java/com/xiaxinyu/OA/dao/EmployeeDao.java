package com.xiaxinyu.OA.dao;

import com.xiaxinyu.OA.entity.Employee;
import org.apache.ibatis.annotations.Param;

/**
 * 员工服务
 */
public interface EmployeeDao {
    /**
     * 根据员工编号查询员工
     * @param employeeId 员工编号
     * @return 员工对象，不存在时返回null
     */
    public Employee selectById(Long employeeId);

    public Employee selectLeader(@Param("emp") Employee employee);
}
