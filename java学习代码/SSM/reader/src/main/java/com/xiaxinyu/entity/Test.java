package com.xiaxinyu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("test") //说明实体类对应哪一张表
public class Test {

    @TableId(type = IdType.AUTO)
    @TableField("id")  //如果字段名和属性名相同，或者符合驼峰命名规则时可省略
    private Integer id;
    @TableField("content")
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
