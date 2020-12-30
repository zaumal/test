package com.test.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.entity.Te;
import com.test.mapper.TeMapper;

@Service
public class TeService {
	@Autowired
	private TeMapper teMapper;
	
	public List<Te> selectList(Te te){
		return teMapper.selectList(te);
	}
	
	public int upate(Te te) {
		return teMapper.update(te);
	}
	
	public int insert(Te te) {
		return teMapper.insert(te);
	}
	
	@Transactional
	public List<Te> selectListForUpdate(Te te) {
		List<Te> tes = teMapper.selectListForUpdate(te);
		
		Te te2 = new Te();
		te2.setId(1);
		te2.setbName("ttttttttt1111");
		upate(te2);
		
		try {
			Thread.sleep(60*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return tes;
	}
	
	private int i = 0;
//	@Scheduled(cron = "${mytask.cron}")
	public void test3() {
		i++;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		System.out.println(i + " " + Thread.currentThread().getName() + " " + sdf.format(new Date()));
		if(i == 3) {
			throw new RuntimeException("================");
		}
	}
}
