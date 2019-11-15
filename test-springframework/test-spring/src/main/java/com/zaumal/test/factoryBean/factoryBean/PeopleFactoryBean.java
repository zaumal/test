package com.zaumal.test.factoryBean.factoryBean;

import org.springframework.beans.factory.FactoryBean;

import com.zaumal.test.factoryBean.entity.People;

public class PeopleFactoryBean implements FactoryBean<People>{
	private String info;

	public PeopleFactoryBean(String info) {
		System.out.println("PeopleFactoryBean 构造方法");
		this.info = info;
	}
	
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public People getObject() throws Exception {
		System.out.println("PeopleFactoryBean getObject ");
		People people = new People();
		String[] infos = info.split(",");
		people.setName(infos[0]);
		people.setAge(Integer.parseInt(infos[1]));
		return people;
	}

	@Override
	public Class<?> getObjectType() {
		return People.class;
	}
}
