package com.xiaxinyu.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description:
 * @author: xiaxinyu
 * @Email: xiaxinyuxy@163.com
 * @date: 2022年11月12日 16:39
 * @Copyright: 个人版权所有
 * @version: 1.0.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author implements Comparable<Author>{
    //id
    private Long id;
    //姓名
    private String name;
    //年龄
    private Integer age;
    //简介
    private String intro;
    //作品
    private List<Book> books;

    @Override
    public int compareTo(Author o) {
        return this.getAge() - o.getAge();
    }
}