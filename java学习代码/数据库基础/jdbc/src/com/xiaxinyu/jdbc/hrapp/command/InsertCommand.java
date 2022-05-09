package com.xiaxinyu.jdbc.hrapp.command;

import com.xiaxinyu.jdbc.common.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class InsertCommand implements Command{
    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.println("请输入员工编号");
        Integer eno = in.nextInt();
        System.out.println("请输入员工姓名");
        String ename = in.next();
        System.out.println("请输入员工薪资");
        Float salary = in.nextFloat();
        System.out.println("请输入员工隶属部门");
        String dname = in.next();
        System.out.println("请输入入职日期");
        String strHiredate = in.next();
        //String 转换 java.sql.Date
        //1.String 转换成 java.util.Date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date udHireDate = null;
        try {
            udHireDate = sdf.parse(strHiredate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //2.将java.util.Date 转换成 java.sql.Date
        Long time = udHireDate.getTime(); //获取1970年到现在的毫秒数
        java.sql.Date sdHiredate = new java.sql.Date(time);

        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "INSERT INTO employee(eno,ename,salary,dname,hiredate) VALUES(?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,eno);
            pstmt.setString(2,ename);
            pstmt.setFloat(3,salary);
            pstmt.setString(4,dname);
            pstmt.setDate(5,sdHiredate); //int,java.sql.Date
            int cnt = pstmt.executeUpdate(); //所有的写操作都要使用executeUpdate方法，包括新增和删除和修改
            System.out.println("员工入职手续已办理");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeConnection(null,pstmt,conn);
        }

    }
}
