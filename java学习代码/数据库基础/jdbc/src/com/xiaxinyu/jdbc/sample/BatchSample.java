package com.xiaxinyu.jdbc.sample;

import com.xiaxinyu.jdbc.common.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 * JDBC中的批处理
 */
public class BatchSample {
    //标准方式插入若干数据
    public static void tc1(){
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            long startTime = new Date().getTime();

            conn = DBUtils.getConnection();
            //JDBC默认使用自动提交模式
            conn.setAutoCommit(false); //关闭自动提交
            String sql = "INSERT INTO employee(eno,ename,salary,dname)  VALUES(?,?,?,?)";
            for(int i = 200000;i < 300000;i ++){

                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1,i);
                pstmt.setString(2,"员工" + i);
                pstmt.setFloat(3,4000);
                pstmt.setString(4,"市场部");
                pstmt.executeUpdate();
            }
            conn.commit(); //提交数据
            long endTime = new Date().getTime();
            System.out.println(endTime - startTime);
        } catch (Exception e){
            e.printStackTrace();
            try {
                if(conn != null && !conn.isClosed())
                    conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }finally {
            DBUtils.closeConnection(null,pstmt,conn);
        }
    }

    //
    public static void tc2(){
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            long startTime = new Date().getTime();
            conn = DBUtils.getConnection();
            //JDBC默认使用自动提交模式
            conn.setAutoCommit(false); //关闭自动提交
            String sql = "INSERT INTO employee(eno,ename,salary,dname)  VALUES(?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            for(int i = 100000;i < 200000;i ++){
                pstmt.setInt(1,i);
                pstmt.setString(2,"员工" + i);
                pstmt.setFloat(3,4000);
                pstmt.setString(4,"市场部");
                pstmt.addBatch(); //将参数加入到批处理任务中
//                pstmt.executeUpdate();
            }
            pstmt.executeBatch(); //执行批处理任务
            conn.commit(); //提交数据
            long endTime  = new Date().getTime();
            System.out.println(endTime - startTime);
        } catch (Exception e){
            e.printStackTrace();
            try {
                if(conn != null && !conn.isClosed())
                    conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }finally {
            DBUtils.closeConnection(null,pstmt,conn);
        }
    }

    public static void main(String[] args) {
        tc1();
        tc2();
    }
}
