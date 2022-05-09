package com.xiaxinyu.jdbc.sample;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.xiaxinyu.jdbc.common.DBUtils;
import com.xiaxinyu.jdbc.hrapp.entity.Employee;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.util.Properties;
import java.util.List;


/**
 *  Apache DBUtils + Druid 连接池联合使用
 */
public class DBUtilsSample {
    private static void query(){
        Properties properties = new Properties();
        String propertyFile = DBUtilsSample.class.getResource("../druid-config.properties").getPath();
        try {
            propertyFile = new URLDecoder().decode(propertyFile,"utf-8");
            properties.load(new FileInputStream(propertyFile));
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            QueryRunner qr = new QueryRunner(dataSource);
            List<Employee> list = qr.query("SELECT * FROM employee LIMIT ?,10",
                    new BeanListHandler<>(Employee.class),
                    new Object[]{10});

            for(Employee e : list){
                System.out.println(e.getEname());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void update(){
        Properties properties = new Properties();
        String propertyFile = DBUtilsSample.class.getResource("../druid-config.properties").getPath();
        Connection conn = null;
        try {
            propertyFile = new URLDecoder().decode(propertyFile,"utf-8");
            properties.load(new FileInputStream(propertyFile));
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            String sql1 = "UPDATE employee SET salary = salary + 1000 WHERE eno = ?";
            String sql2 = "UPDATE employee SET salary = salary - 500 WHERE eno = ?";
            QueryRunner qr = new QueryRunner();
            qr.update(conn,sql1,new Object[]{1111});
            qr.update(conn,sql2,new Object[]{3308});
            conn.commit();

        } catch (Exception e) {
            e.printStackTrace();
            try{
                if(conn != null) conn.rollback();
            }catch(Exception e1){
                e1.printStackTrace();
            }
        }finally{
            DBUtils.closeConnection(null,null,conn);
        }
    }

    public static void main(String[] args){
//        DBUtilsSample.query();
        DBUtilsSample.update();
    }
}
