package com.zaumal.test.mvc.initializer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.zaumal.test.mvc.initializer.entity.People;

public interface PeopleRepository extends GenericRepository<People, Integer>{
	@Query
	public List<People> findByName(String name);
}
