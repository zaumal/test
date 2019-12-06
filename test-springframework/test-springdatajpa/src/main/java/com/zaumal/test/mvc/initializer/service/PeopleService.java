package com.zaumal.test.mvc.initializer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zaumal.test.mvc.initializer.entity.People;
import com.zaumal.test.mvc.initializer.repository.GenericRepository;
import com.zaumal.test.mvc.initializer.repository.PeopleRepository;

@Service
public class PeopleService extends AbstractService<People,Integer>{
	@Autowired
	private PeopleRepository peopleRepository;

	@Override
	protected GenericRepository<People, Integer> getRepository() {
		return peopleRepository;
	}
	
	public List<People> findByName(String name){
		return peopleRepository.findByName(name);
	}
}