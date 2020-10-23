package com.zaumal.test.mybatis.entity;

import java.io.Serializable;

public class Apple implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String color;
	private Integer size;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
}
