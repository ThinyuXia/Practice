package com.xiaxinyu.jdbc.hrapp.entity;

import java.sql.*;

public class JDBCTooL {
    /**
     *创建新的数据库连接
     * @return Connection对象
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        //1.加载并注册JDBC驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.创建数据库连接
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/x?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai",
                "root",
                "xiaxinyv6");
        return conn;
    }

    /**
     * 关闭连接，释放资源
     * @param res 结果集对象
     * @param stmt Statement对象
     * @param conn Connection对象
     */
    public static void closeConnection(ResultSet res, Statement stmt,Connection conn){
        try {
            if (res != null) res.close();
            if (stmt != null) stmt.close();
            if (conn != null && !conn.isClosed()) conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
