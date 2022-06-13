package com.xiaxinyu.jdbc.hrapp.entity;

import java.util.*;
public class TestMain {
    public static void main(String[] args){
        StudentBusiness studentBusiness = new StudentBusiness();
        Scanner in = new Scanner(System.in);
        while(true){
            System.out.println("请输入要执行的操作： 1 新增学生 2 查看所有学生信息 3 退出");
            String s = in.next();
            if(s.equals("1")){
                System.out.println("请输入新增学生学号：");
                String serial = in.next();
                System.out.println("请输入新增学生姓名：");
                String name = in.next();
                System.out.println("请输入新增学生班级：");
                String classes = in.next();
                studentBusiness.insert(serial,name,classes);
            }
            else if(s.equals("2")){
                studentBusiness.show();
            }else{
                break;
            }

        }
    }
}
