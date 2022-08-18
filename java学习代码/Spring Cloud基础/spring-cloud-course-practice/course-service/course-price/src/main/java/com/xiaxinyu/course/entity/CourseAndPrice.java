package com.xiaxinyu.course.entity;

import java.io.Serializable;

public class CourseAndPrice implements Serializable {
    Integer id;
    Integer courseId;
    Float price;
    String courseName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return "CourseAndPrice{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", price=" + price +
                ", courseName='" + courseName + '\'' +
                '}';
    }
}
