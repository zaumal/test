package com.zaumal.test.mybatis.entity;

import java.io.Serializable;

public class People implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String sex;
	private Apple eat;
	
	public Apple getEat() {
		return eat;
	}
	public void setEat(Apple eat) {
		this.eat = eat;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
}
