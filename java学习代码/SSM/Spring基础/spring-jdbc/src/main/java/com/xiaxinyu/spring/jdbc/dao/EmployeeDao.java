package com.xiaxinyu.spring.jdbc.dao;

import com.xiaxinyu.spring.jdbc.entity.Employee;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.util.List;
import java.util.Map;

public class EmployeeDao {

    private JdbcTemplate jdbcTemplate;

    public Employee findById(Integer eno){
        String sql = "SELECT * FROM employee WHERE eno = ?";
        Employee employee = jdbcTemplate.queryForObject(sql,new Object[]{eno},new BeanPropertyRowMapper<Employee>(Employee.class));
        return employee;
    }

    public List<Employee> findByDname(String dname){
        String sql = "SELECT * FROM employee WHERE dname = ?";
        List<Employee> list = jdbcTemplate.query(sql,new Object[]{dname},new BeanPropertyRowMapper<Employee>(Employee.class));
        return list;
    }

    public List findMapByDname(String dname){
        String sql = "SELECT eno as empno, salary as s FROM employee WHERE dname = ?";
        List<Map<String,Object>> maps = jdbcTemplate.queryForList(sql,new Object[]{dname});
        return maps;
    }

    public void insert(Employee employee){
        String sql = "INSERT INTO employee(eno,ename,salary,dname,hiredate) values(?,?,?,?,?)";
        jdbcTemplate.update(sql,new Object[]{employee.getEno(),employee.getEname(),employee.getSalary(),employee.getDname(),employee.getHiredate()});
    }

    public int update(Employee employee){
        String sql = "UPDATE employee SET ename = ?,salary = ?,dname=?,hiredate=? WHERE eno = ?";
        int count = jdbcTemplate.update(sql,new Object[]{employee.getEname(),employee.getSalary(),employee.getDname(),employee.getHiredate(),employee.getEno()});
        return count;
    }

    public int delete(Integer eno){
        String  sql = "DELETE FROM employee WHERE eno = ?";
        return jdbcTemplate.update(sql,new Object[]{eno});
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

}
