package com.zaumal.test.demo.comm.service;

import java.io.Serializable;
import java.util.Optional;

import com.zaumal.test.demo.comm.dao.GenericRepository;
import com.zaumal.test.demo.comm.exception.ObjectNotFoundException;

public abstract class BaseService<T, ID extends Serializable> extends AbstractService{
	abstract protected GenericRepository<T, ID> getRepository();
	
	public T save(T t){
		return getRepository().save(t);
	}
	
	public void saveAll(Iterable<T> entities){
		getRepository().saveAll(entities);
	}
	
	
	public void delete(T t){
//		if(!getRepository().existsById(getID(t))){
//			throw new ObjectNotFoundException();
//		}
		getRepository().delete(t);
	}
	
	public void delete(ID id){
		if(getRepository().existsById(id)){
			getRepository().deleteById(id);
		}
	}

	public void deleteAll(Iterable<T> entities){
		getRepository().deleteAll(entities);
	}
	
	public T getById(ID id){
		Optional<T> findObj = getRepository().findById(id);
		if (findObj.isPresent()) {
			return findObj.get();
		}
		return null;
	}
	
	public T findById(ID id){
		Optional<T> findObj = getRepository().findById(id);
		if (findObj.isPresent()) {
			return findObj.get();
		}
		throw new ObjectNotFoundException();
	}
	
	public ID getID(T t){
		@SuppressWarnings("unchecked")
		ID id = (ID)getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(t);
		return id;
	}
}
