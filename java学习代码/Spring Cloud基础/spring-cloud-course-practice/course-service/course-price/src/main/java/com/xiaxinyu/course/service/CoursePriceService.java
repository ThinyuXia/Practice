package com.xiaxinyu.course.service;

import com.xiaxinyu.course.entity.CourseAndPrice;
import com.xiaxinyu.course.entity.CoursePrice;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CoursePriceService {
    CoursePrice getCoursePrice(Integer courseId);
    List<CourseAndPrice> getCourseAndPrice();
}
