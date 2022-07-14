package com.xiaxinyu.restful.controller;

import com.xiaxinyu.springmvc.entity.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/restful")
public class RestfulController {

    @GetMapping("/request")
    @ResponseBody
    public String doGetRequest(){
        return "{\"message\":\"返回查询结果\"}";
    }

    @PostMapping("/request/{rid}")
    @ResponseBody
    public String doPostRequest(@PathVariable("rid")  Integer requestId, Person person){
        System.out.println(person.getName() + " : " + person.getAge());
        return "{\"message\":\"数据新建成功\",\"id\":" + requestId + "}";
    }

    @DeleteMapping("/request")
    @ResponseBody
    public String doDeleteRequest(){
        return "{\"message\":\"数据删除成功\"}";
    }

    @PutMapping("/request")
    @ResponseBody
    public String doPutRequest(Person person){
        System.out.println(person.getName() + " : " + person.getAge());
        return "{\"message\":\"数据修改成功\"}";
    }

    @GetMapping("/person")
    @ResponseBody
    public Person findByPersonId(Integer id){
        Person p = new Person();
        if(id == 1){
            p.setName("Lily");
            p.setAge(23);
        }else{
            p.setName("Smith");
            p.setAge(22);
        }
        return p;
    }

    @GetMapping("/persons")
    @ResponseBody
    public List<Person> findByPersons(Integer id){

        List list = new ArrayList<Person>();
        Person p1 = new Person();
        p1.setName("Lily");
        p1.setAge(23);
        p1.setBirthday(new Date());
        Person p2 = new Person();
        p2.setName("Smith");
        p2.setAge(22);
        p2.setBirthday(new Date());
        list.add(p1);
        list.add(p2);
        return list;
    }

}
