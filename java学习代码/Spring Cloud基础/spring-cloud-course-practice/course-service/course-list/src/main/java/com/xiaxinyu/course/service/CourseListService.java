package com.xiaxinyu.course.service;


import com.xiaxinyu.course.entity.Course;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CourseListService {
    List<Course> getCourseList();
}
