package com.xiaxinyu.jdbc.hrapp.command;


import com.xiaxinyu.jdbc.common.DBUtils;
import com.xiaxinyu.jdbc.hrapp.entity.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class PaginationCommand implements  Command{
    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.println("请输入页号");
        int page = in.nextInt();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Employee> emps = new ArrayList<>();


        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT * FROM EMPLOYEE LIMIT ?,10";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,(page - 1) * 10);
            rs = pstmt.executeQuery();
            while(rs.next()){
                Integer eno = rs.getInt("eno");
                String ename = rs.getString("ename");
                Float salary = rs.getFloat("salary");
                String dname = rs.getString("dname");
                // jdbc中获取日期使用java.sql.Date 继承 java.util.Date
                Date hiredate = rs.getDate("hiredate");
                Employee emp = new Employee();
                emp.setEno(eno);
                emp.setEname(ename);
                emp.setSalary(salary);
                emp.setDname(dname);
                emp.setHiredate(hiredate);
                emps.add(emp);
            }
            System.out.println(emps.size());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally{
            DBUtils.closeConnection(rs,pstmt,conn);
        }


    }
}
