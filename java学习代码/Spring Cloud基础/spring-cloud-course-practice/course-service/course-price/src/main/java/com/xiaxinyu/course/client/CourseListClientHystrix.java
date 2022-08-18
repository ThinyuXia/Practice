package com.xiaxinyu.course.client;

import com.xiaxinyu.course.entity.Course;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
public class CourseListClientHystrix implements CourseListClient{
    @Override
    public List<Course> courseList() {
        List<Course> defaultCourses = new ArrayList<>();
        Course course = new Course();
        course.setId(1);
        course.setCourseId(1);
        course.setCourseName("默认课程");
        course.setValid(1);
        defaultCourses.add(course);
//        return Collections.emptyList();
        return defaultCourses;
    }
}
