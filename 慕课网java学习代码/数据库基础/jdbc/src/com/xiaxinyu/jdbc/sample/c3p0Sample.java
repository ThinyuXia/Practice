package com.xiaxinyu.jdbc.sample;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.xiaxinyu.jdbc.common.DBUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class c3p0Sample {
    public static void main(String[] args){
        //1.加载配置文件
        //2.创建DataSource数据源
        DataSource dataSource =  new ComboPooledDataSource();
        //3.创建数据库连接
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement("SELECT * FROM employee LIMIT 10");
            rs = pstmt.executeQuery(); // 这一步才会创建连接
            while(rs.next()){
                String ename = rs.getString("ename");
                System.out.println(ename);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            DBUtils.closeConnection(rs,pstmt,conn);
        }
    }
}
