package com.xiaxinyu.jdbc.hrapp.command;

import java.sql.*;
import java.util.Scanner;

public class PstmtQueryCommand implements Command {

    @Override
    public void execute() {
        System.out.println("请输入部门名称");
        Scanner in = new Scanner(System.in);
        String pdname = in.next();
        Connection conn = null;
//        Statement stmt = null;
        PreparedStatement pstmt = null;
        ResultSet res = null;
        try {
            //1.加载并注册JDBC驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.创建数据库连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc?useSSL=false&useUnicode=true?characterEncoding=utf-8?serverTimezone=Asia/Shanghai?allowPublicKeyRetrieval=true", "root", "xiaxinyv6");
            //3.创建Statement对象
            String sql = "SELECT * FROM employee WHERE dname=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,pdname);
            res = pstmt.executeQuery();
            //4.遍历查询结果
            //res.next()返回是否存在下一条记录的布尔值，如果有返回true并提取下一条数据，如果没有，则返回false；
            while (res.next()) {
                Integer eno = res.getInt(1);
                String ename = res.getString("ename");
                Float salary = res.getFloat("salary");
                String dname = res.getString("dname");
                System.out.println(dname + "-" + eno + "-" + ename + "-" + salary);
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
