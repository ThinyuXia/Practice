package com.xiaxinyu.jdbc.sample;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.xiaxinyu.jdbc.common.DBUtils;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class DruidSample {
    public static void main(String[] args){
        //1.加载属性文件
        Properties properties = new Properties();
        //获取当前类路径下的指定文件的路径
        String propertyFile = DruidSample.class.getResource("../druid-config.properties").getPath();
        //空格->%20
        try {
            propertyFile = new URLDecoder().decode(propertyFile,"utf-8");
            properties.load(new FileInputStream(propertyFile));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            //2.获取DataSource数据源对象
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            //3.获取数据库连接
            conn = dataSource.getConnection(); //对连接进行初始化
            pstmt = conn.prepareStatement("SELECT * FROM employee LIMIT 10"); //正式创建连接！！！
            rs = pstmt.executeQuery();
            while(rs.next()){
                String ename = rs.getString("ename");
                System.out.println(ename);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            /**
             * 不使用连接池时，conn.close()关闭连接
             * 使用连接池时，conn.close()将当前连接回收至连接池
             */
            DBUtils.closeConnection(rs,pstmt,conn);
        }
    }
}
