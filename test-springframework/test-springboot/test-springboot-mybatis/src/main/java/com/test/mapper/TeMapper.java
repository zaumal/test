package com.test.mapper;

import java.util.List;

import com.test.entity.Te;

public interface TeMapper {
	List<Te> selectList(Te te);
	
	int update(Te te);
	
	List<Te> selectListForUpdate(Te te);
	
	int insert(Te te);
}
