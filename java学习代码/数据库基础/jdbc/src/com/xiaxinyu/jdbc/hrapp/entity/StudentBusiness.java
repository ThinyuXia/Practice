package com.xiaxinyu.jdbc.hrapp.entity;

public class StudentBusiness {
    private StudentDAO studentDAO = new StudentDAO();
    public void insert(String serial,String name,String classes){
        studentDAO.insert(serial,name,classes);
    }

    public void show(){
        studentDAO.show();
    }
}
