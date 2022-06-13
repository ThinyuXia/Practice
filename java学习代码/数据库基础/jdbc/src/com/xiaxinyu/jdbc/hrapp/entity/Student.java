package com.xiaxinyu.jdbc.hrapp.entity;

public class Student {
    private Integer id;
    private String serial;
    private String name;
    private String classes;

    public Student() {
    }

    public Student(Integer id, String serial, String name, String classes) {
        this.id = id;
        this.serial = serial;
        this.name = name;
        this.classes = classes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }
}
