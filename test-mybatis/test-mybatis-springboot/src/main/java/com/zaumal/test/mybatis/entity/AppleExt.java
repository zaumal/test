package com.zaumal.test.mybatis.entity;

import java.util.List;

public class AppleExt extends Apple{
	private static final long serialVersionUID = 1L;
	private People belong;
	private List<People> eaters;
	
	public People getBelong() {
		return belong;
	}
	public void setBelong(People belong) {
		this.belong = belong;
	}
	public List<People> getEaters() {
		return eaters;
	}
	public void setEaters(List<People> eaters) {
		this.eaters = eaters;
	}
	
	@Override
	public String toString() {
		String s = "id : " + getId() + ", color : " + getColor() + ", size : " + getSize() + ";<br>"
				+ "belong : [ id : " + belong.getId() + ", name : " + belong.getName() + ", sex : " + belong.getSex() + "];<br>"
						+ "";
		if(null != eaters) {
			s += "eaters : [<br>";
			for(int i = 0; i < eaters.size(); i++) {
				People eater = eaters.get(i);
				s += ("eater : [ id : " + eater.getId() + ", name : " + eater.getName() + ", sex : " + eater.getSex() + "];<br>");
			}
			s += "];";
		}
		return s;
	}
}
