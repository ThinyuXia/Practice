package com.xiaxinyu.course.service.impl;

import com.xiaxinyu.course.dao.CourseMapper;
import com.xiaxinyu.course.entity.Course;
import com.xiaxinyu.course.service.CourseListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseListServiceImpl implements CourseListService {

    @Autowired
    CourseMapper courseMapper;

    @Override
    public List<Course> getCourseList() {
        return courseMapper.findValidCourses();
    }
}
