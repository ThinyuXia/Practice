package com.xiaxinyu.freemarker.servlet;

public class Employee {
	private Integer empno;
	private String ename;
	private String edepartment;
	private String job;
	private Double salary;
	
	public Employee() {}
	
	
	public Employee(Integer empno, String ename, String edepartment, String job, Double salary) {
		super();
		this.empno = empno;
		this.ename = ename;
		this.edepartment = edepartment;
		this.job = job;
		this.salary = salary;
	}
	public Integer getEmpno() {
		return empno;
	}
	public void setEmpno(Integer empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getEdepartment() {
		return edepartment;
	}
	public void setEdepartment(String edepartment) {
		this.edepartment = edepartment;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	
}
