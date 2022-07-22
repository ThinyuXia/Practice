package com.xiaxinyu.hellospring;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface StudentMapper {
    @Select("SELECT * FROM students WHERE id = #{id}")
    Student findById(Integer id);
}
