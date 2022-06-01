package com.xiaxinyu.ajax;

public class Employee {
	private String name;
	private String job;
	private String department;
	
	public Employee() {
		
	}
	
	public Employee(String name, String job, String department) {
		super();
		this.name = name;
		this.job = job;
		this.department = department;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	
}
