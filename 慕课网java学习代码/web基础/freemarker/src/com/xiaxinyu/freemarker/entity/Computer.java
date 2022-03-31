package com.xiaxinyu.freemarker.entity;

import java.util.Map;

public class Computer {
	private String model;
	private int state;
	private Map info;
	private float price;
	
	public Computer() {
	}
	
	public Computer(String model, int state, Map info, float price) {
		super();
		this.model = model;
		this.state = state;
		this.info = info;
		this.price = price;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Map getInfo() {
		return info;
	}
	public void setInfo(Map info) {
		this.info = info;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	
}
