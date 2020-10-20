package com.zaumal.test.demo.sk.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zaumal.test.demo.comm.dao.GenericRepository;
import com.zaumal.test.demo.comm.service.BaseService;
import com.zaumal.test.demo.sk.dao.OrderDao;
import com.zaumal.test.demo.sk.entity.Order;
import com.zaumal.test.demo.sk.entity.Stock;

@Service
public class OrderService extends BaseService<Order, Integer>{
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private StockService stockService;
	
	@Override
	protected GenericRepository<Order, Integer> getRepository() {
		return orderDao;
	}

	@Transactional
	public int createOrder(Integer sid) {
		//检验库存
		Stock stock = stockService.checkStock(sid);
		//卖库存
		stockService.saleStock(stock);
		//创建订单
		return crateOrder(stock);
	}

	private int crateOrder(Stock stock) {
		Order order = new Order();
		order.setSid(stock.getId());
		order.setName(stock.getName());
		order.setCreateTime(new Date());
		
		order = orderDao.save(order);
		
		return order.getId();
	}
	
}
