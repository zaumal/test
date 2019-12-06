package com.zaumal.test.mvc.initializer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.zaumal.test.mvc.initializer.entity.People;
import com.zaumal.test.mvc.initializer.service.PeopleService;

@RestController
public class TestController {
	@Autowired
	private PeopleService peopleService;
	
	@GetMapping("/{id}")
	public People test(@PathVariable Integer id) {
		People people = peopleService.findById(id);
		return people;
	}
	
	@GetMapping("/find/{name}")
	public List<People> test2(@PathVariable String name) {
		List<People> peoples = peopleService.findByName(name);
		return peoples;
	}
}
