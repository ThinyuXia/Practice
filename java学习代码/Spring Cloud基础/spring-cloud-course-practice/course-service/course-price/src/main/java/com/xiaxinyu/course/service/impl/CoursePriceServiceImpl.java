package com.xiaxinyu.course.service.impl;

import com.xiaxinyu.course.client.CourseListClient;
import com.xiaxinyu.course.dao.CoursePriceMapper;
import com.xiaxinyu.course.entity.Course;
import com.xiaxinyu.course.entity.CourseAndPrice;
import com.xiaxinyu.course.entity.CoursePrice;
import com.xiaxinyu.course.service.CoursePriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoursePriceServiceImpl implements CoursePriceService {

    @Autowired
    CoursePriceMapper coursePriceMapper;

    @Autowired
    CourseListClient courseListClient;

    @Override
    public CoursePrice getCoursePrice(Integer courseId) {
        return coursePriceMapper.findCoursePrice(courseId);
    }

    @Override
    public List<CourseAndPrice> getCourseAndPrice() {
        List<Course> courses = courseListClient.courseList();

        List<CourseAndPrice> courseAndPriceList = new ArrayList<>();
        for(int i = 0;i < courses.size();i ++){
            Course course = courses.get(i);
            if(course != null){
                Float price = coursePriceMapper.findCoursePrice(course.getCourseId()).getPrice();
                CourseAndPrice courseAndPrice = new CourseAndPrice();
                courseAndPrice.setPrice(price);
                courseAndPrice.setCourseName(course.getCourseName());
                courseAndPrice.setCourseId(course.getCourseId());
                courseAndPrice.setId(course.getId());
                courseAndPriceList.add(courseAndPrice);
            }
        }
        return courseAndPriceList;
    }
}
