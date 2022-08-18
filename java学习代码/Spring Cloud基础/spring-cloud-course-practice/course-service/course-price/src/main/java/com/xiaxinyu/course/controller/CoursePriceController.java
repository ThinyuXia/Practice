package com.xiaxinyu.course.controller;

import com.xiaxinyu.course.client.CourseListClient;
import com.xiaxinyu.course.dao.CoursePriceMapper;
import com.xiaxinyu.course.entity.Course;
import com.xiaxinyu.course.entity.CourseAndPrice;
import com.xiaxinyu.course.entity.CoursePrice;
import com.xiaxinyu.course.service.CoursePriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CoursePriceController {

    @Autowired
    CoursePriceService coursePriceService;

    @Autowired
    CourseListClient courseListClient;


    @GetMapping("/price")
    public Float getCoursePrice(Integer courseId){
        return coursePriceService.getCoursePrice(courseId).getPrice();
    }

    @GetMapping("/courseInPrice")
    public List<Course> getCourseListInPrice(Integer courseId){
        return courseListClient.courseList();
    }

    @GetMapping("/courseAndPrice")
    public List<CourseAndPrice> getCoursesAndPrice(){
        return coursePriceService.getCourseAndPrice();
    }

}
