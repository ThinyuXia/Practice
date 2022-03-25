package com.xiaxinyu.json;

import java.util.Calendar;

import com.alibaba.fastjson.JSON;

public class FastJsonSample1 {
	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		c.set(2023, 6, 1, 0, 0, 0);
		Employee emp = new Employee(3644,"xiaxinyu","研发工程师",c.getTime(),45000f,"研发部");
		//FastJSON中提供了JSON对象，完成对象与JSON字符串的互相转换
		String json = JSON.toJSONString(emp);
		System.out.println(json);
		Employee result = JSON.parseObject(json,Employee.class);
		System.out.println(result.getDname());
	}
}
