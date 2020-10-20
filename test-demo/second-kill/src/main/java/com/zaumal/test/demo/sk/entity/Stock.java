package com.zaumal.test.demo.sk.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="second_kill_stock")
@NamedQuery(name="Stock.findAll", query="SELECT l FROM Stock l")
public class Stock implements Serializable{
	private static final long serialVersionUID = 1877318853864888539L;
	
	@Id
	private int id;
	private String name;
	private int count;
	private int sale;
	private int version;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getSale() {
		return sale;
	}
	public void setSale(int sale) {
		this.sale = sale;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
}
