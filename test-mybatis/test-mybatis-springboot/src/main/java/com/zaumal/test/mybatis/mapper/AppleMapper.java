package com.zaumal.test.mybatis.mapper;

import org.apache.ibatis.annotations.Param;

import com.zaumal.test.mybatis.entity.AppleExt;

public interface AppleMapper {
	public AppleExt xmlQueryById(@Param("id") Integer id);
}
