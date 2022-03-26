package com.xiaxinyu.ajax;

public class News {
	private String title;
	private String Date;
	private String source;
	private String content;
	
	public News() {
		
	}
	
	public News(String title, String date, String source, String content) {
		super();
		this.title = title;
		Date = date;
		this.source = source;
		this.content = content;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
