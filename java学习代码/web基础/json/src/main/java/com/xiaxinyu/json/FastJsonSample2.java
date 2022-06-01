package com.xiaxinyu.json;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

public class FastJsonSample2 {
	public static void main(String[] args) {
		List emplist = new ArrayList();
		for(int i = 1;i <=  100;i ++) {
			Employee emp = new Employee(3644 + i,"员工" + i);
			emplist.add(emp);
		}
		String json = JSON.toJSONString(emplist);
		System.out.println(json);
		List<Employee> emps = JSON.parseArray(json,Employee.class);
		for(Employee emp : emps){
			System.out.println(emp.getEname());
		}
	}
}
