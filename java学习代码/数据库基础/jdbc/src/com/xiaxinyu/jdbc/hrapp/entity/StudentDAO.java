package com.xiaxinyu.jdbc.hrapp.entity;

import com.xiaxinyu.jdbc.common.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO {
    ResultSet res = null;
    Connection conn = null;
    PreparedStatement pstmt = null;
    public void insert(String serial,String name,String classes){
        try {
            conn = JDBCTooL.getConnection();
            String sql = "INSERT INTO t_student(serial,name,classes) VALUES(?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,serial);
            pstmt.setString(2,name);
            pstmt.setString(3,classes);
            int cnt = pstmt.executeUpdate(); //所有的写操作都要使用executeUpdate方法，包括新增和删除和修改
            System.out.println("新增学生操作已完成");
            System.out.println("数据库中所有学生信息如下");
            System.out.println("--------------------");
            show();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeConnection(null,pstmt,conn);
        }
    }

    public void show(){
        try {
            conn = JDBCTooL.getConnection();

            String sql = "SELECT * FROM t_student";
            pstmt = conn.prepareStatement(sql);
            res = pstmt.executeQuery();
            //4.遍历查询结果
            //res.next()返回是否存在下一条记录的布尔值，如果有返回true并提取下一条数据，如果没有，则返回false；
            while (res.next()) {
                Integer id = res.getInt(1);
                String serial = res.getString("serial");
                String name = res.getString("name");
                String classes = res.getString("classes");
                System.out.println(id + "-" + serial + "-" + name + "-" + classes);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (res != null) res.close();
                if (pstmt != null) pstmt.close();
                if (conn != null && !conn.isClosed()) conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
