package com.xiaxinyu.spring.jdbc.service;

import com.xiaxinyu.spring.jdbc.dao.EmployeeDao;
import com.xiaxinyu.spring.jdbc.entity.Employee;

import java.util.Date;

public class BatchService {
    private EmployeeDao employeeDao;

    public void importJob1(){
        for(int i = 1;i <= 10;i ++){
            Employee employee = new Employee();
            employee.setEno(8000 + i);
            employee.setEname("研发部员工" + i);
            employee.setSalary(4000f);
            employee.setDname("研发部");
            employee.setHiredate(new Date());
            employeeDao.insert(employee);
        }
    }

    public void importJob2(){
        for(int i = 1;i <= 10;i ++){
            Employee employee = new Employee();
            employee.setEno(9000 + i);
            employee.setEname("市场部员工" + i);
            employee.setSalary(3000f);
            employee.setDname("市场部");
            employee.setHiredate(new Date());
            employeeDao.insert(employee);
        }
    }



    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }
}
