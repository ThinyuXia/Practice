package com.xiaxinyu.employee;

public class Employee {
	private Integer id;
	private String ename;
	private String department;
	private String job;
	private Float salary;
	
	public Employee(Integer id, String ename, String department, String job, Float salary) {
		this.id = id;
		this.ename = ename;
		this.department = department;
		this.job = job;
		this.salary = salary;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Float getSalary() {
		return salary;
	}
	public void setSalary(Float salary) {
		this.salary = salary;
	}
	
}
