package com.xiaxinyu.jstl;

public class Company {
	private String cname;
	private String curl;
	
	
	
	public Company(String cname, String curl) {
		super();
		this.cname = cname;
		this.curl = curl;
	}
	
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCurl() {
		return curl;
	}
	public void setCurl(String curl) {
		this.curl = curl;
	}
	
}
