package com.xiaxinyu.course.controller;

import com.xiaxinyu.course.entity.Course;
import com.xiaxinyu.course.service.CourseListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class CourseListController {

    @Autowired
    CourseListService courseListService;

    @GetMapping("/courses")
    public List<Course> courseList(){
        return courseListService.getCourseList();
    }
}
