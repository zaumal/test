package com.zaumal.test.demo.sk.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.zaumal.test.demo.comm.dao.GenericRepository;
import com.zaumal.test.demo.sk.entity.Stock;

public interface StockDao extends GenericRepository<Stock,Integer>{
	@Modifying
	@Query("update Stock a set a.sale=?1,a.version=?2 where a.id=?3 and a.version=?4")
	public int saveOptimistic(int newSale,int newVersion,int id,int version);
}
