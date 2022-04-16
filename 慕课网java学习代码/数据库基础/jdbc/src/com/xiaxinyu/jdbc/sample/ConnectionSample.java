package com.xiaxinyu.jdbc.sample;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionSample {
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/jdbc?useSSL=false&useUnicode=true?characterEncoding=utf-8?serverTimezone=Asia/Shanghai?allowPublicKeyRetrieval=true";
            Connection conn = DriverManager.getConnection(url,"root","xiaxinyv6");
            System.out.println(conn);
        }catch(Exception e){
            e.printStackTrace();
        }


    }
}
