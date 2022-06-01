package com.xiaxinyu.json;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class Employee {
	private Integer enm;
	private String ename;
	private String job;
	@JSONField(name="hiredate",format="yyyy-MM-dd")
	private Date hdate;
	private Float salary;
	@JSONField(serialize=false) //不对该字段进行序列化
	private String dname;
	
	
	
	
	public Employee(Integer enm, String ename) {
		super();
		this.enm = enm;
		this.ename = ename;
	}
	public Employee(Integer enm, String ename, String job, Date hdate, Float salary, String dname) {
		super();
		this.enm = enm;
		this.ename = ename;
		this.job = job;
		this.hdate = hdate;
		this.salary = salary;
		this.dname = dname;
	}
	public Integer getEnm() {
		return enm;
	}
	public void setEnm(Integer enm) {
		this.enm = enm;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Date getHdate() {
		return hdate;
	}
	public void setHdate(Date hdate) {
		this.hdate = hdate;
	}
	public Float getSalary() {
		return salary;
	}
	public void setSalary(Float salary) {
		this.salary = salary;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	
}
