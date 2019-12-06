package com.zaumal.test.mvc.initializer.service;

import java.io.Serializable;
import java.util.Optional;

import com.zaumal.test.mvc.initializer.repository.GenericRepository;

public abstract class AbstractService<T,ID extends Serializable>{
	abstract protected GenericRepository<T, ID> getRepository();
	
	public T findById(ID id) {
		GenericRepository<T, ID> repository = getRepository();
		Optional<T> findObj=  repository.findById(id);
		if(findObj.isPresent()) {
			return findObj.get();
		}
		return null;
	}
}
