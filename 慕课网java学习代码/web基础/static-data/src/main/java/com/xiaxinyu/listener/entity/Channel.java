package com.xiaxinyu.listener.entity;

public class Channel {
	private String cName;
	private String cUrl;
	
	public Channel() {
	}
	
	public Channel(String cName, String cUrl) {
		super();
		this.cName = cName;
		this.cUrl = cUrl;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getcUrl() {
		return cUrl;
	}
	public void setcUrl(String cUrl) {
		this.cUrl = cUrl;
	}
	
}
