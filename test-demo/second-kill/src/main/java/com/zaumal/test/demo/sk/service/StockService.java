package com.zaumal.test.demo.sk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zaumal.test.demo.comm.dao.GenericRepository;
import com.zaumal.test.demo.comm.service.BaseService;
import com.zaumal.test.demo.sk.dao.StockDao;
import com.zaumal.test.demo.sk.entity.Stock;

@Service
public class StockService extends BaseService<Stock, Integer>{
	@Autowired
	private StockDao stockDao;
	
	@Override
	protected GenericRepository<Stock, Integer> getRepository() {
		return stockDao;
	}

	public Stock checkStock(Integer id) {
		Stock stock = super.findById(id);
		if(null != stock && stock.getCount() > stock.getSale()) {
			return stock;
		}
		throw new RuntimeException("库存不足");
	}
	
	public void saleStock(Stock stock) {
		stock.setSale(stock.getSale()+1);
		stockDao.save(stock);
	}
	
}
