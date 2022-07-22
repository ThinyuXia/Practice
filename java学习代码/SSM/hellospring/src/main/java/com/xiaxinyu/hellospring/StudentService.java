package com.xiaxinyu.hellospring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("studentService")
public class StudentService {
    @Autowired
    StudentMapper studentMapper;
    public Student findStudent(Integer id){
        Student student = studentMapper.findById(id);
        return student;
    }
}
