package com.zaumal.test.springboot.expand.listener.event;

import org.springframework.context.ApplicationEvent;

public class MyApplicationEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1750322966606478758L;
	private String my;
	
	public MyApplicationEvent(Object source) {
		super(source);
	}
	
	public MyApplicationEvent(Object source,String my) {
		super(source);
		this.my = my;
	}
	
	public void myEvent() {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>> MyApplicationEvent myEvent : " + my);
	}

	public String getMy() {
		return my;
	}

	public void setMy(String my) {
		this.my = my;
	}
}
