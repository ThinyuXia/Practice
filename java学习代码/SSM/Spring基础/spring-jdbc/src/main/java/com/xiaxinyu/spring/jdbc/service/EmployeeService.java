package com.xiaxinyu.spring.jdbc.service;

import com.xiaxinyu.spring.jdbc.dao.EmployeeDao;
import com.xiaxinyu.spring.jdbc.entity.Employee;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.Date;

public class EmployeeService {
    private EmployeeDao employeeDao;
    private BatchService batchService;
    private DataSourceTransactionManager transactionManager;

    public void batchImport(){
        TransactionDefinition definition = new DefaultTransactionDefinition();
        //开启一个事务，返回事务状态，事务状态说明当前事务的执行阶段
        TransactionStatus status = transactionManager.getTransaction(definition);


        try {
            for(int i = 1;i <= 10;i ++){
                Employee employee = new Employee();
                if(i == 3){
                    throw new RuntimeException("111");
                }
                employee.setEno(8000 + i);
                employee.setEname("员工" + i);
                employee.setSalary(4000f);
                employee.setDname("市场部");
                employee.setHiredate(new Date());

                employeeDao.insert(employee);
            }
            //提交事务
            transactionManager.commit(status);
        } catch (RuntimeException e) {
            //回滚事务
            transactionManager.rollback(status);
            e.printStackTrace();
        }


    }


    public void startImportJob(){
        batchService.importJob1();
        batchService.importJob2();
        System.out.println("批量导入成功");
    }

    public BatchService getBatchService() {
        return batchService;
    }

    public void setBatchService(BatchService batchService) {
        this.batchService = batchService;
    }

    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }


    public DataSourceTransactionManager getTransactionManager() {
        return transactionManager;
    }

    public void setTransactionManager(DataSourceTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }
}
