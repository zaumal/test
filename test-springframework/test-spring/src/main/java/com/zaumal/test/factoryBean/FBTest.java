package com.zaumal.test.factoryBean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zaumal.test.factoryBean.conf.AppConf;
import com.zaumal.test.factoryBean.entity.People;
import com.zaumal.test.factoryBean.factoryBean.PeopleFactoryBean;

public class FBTest {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConf.class);
		System.out.println("============================");
		PeopleFactoryBean peopleFactoryBean = ac.getBean("&&&&&peopleFactoryBean",PeopleFactoryBean.class);
		System.out.println(peopleFactoryBean.getClass());
		System.out.println("===================================");
		People people = ac.getBean("peopleFactoryBean",People.class);
		System.out.println(people.getName() + " : " + people.getAge());
		People people2 = ac.getBean("peopleFactoryBean",People.class);
		System.out.println(people2.getName() + " : " + people2.getAge());
	}
}
