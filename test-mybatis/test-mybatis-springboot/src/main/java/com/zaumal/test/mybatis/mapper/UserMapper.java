package com.zaumal.test.mybatis.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.zaumal.test.mybatis.entity.User;
import com.zaumal.test.mybatis.sql.UserSql;

@Mapper
public interface UserMapper {
	//测试三种sql方式
	@Select("select age from test_mybatis_user where name=#{name}")
	public Integer annotationQueryAgeByName(@Param("name") String name);
	
	public Integer xmlQueryAgeByName(@Param("name") String name);
	
	@SelectProvider(type = UserSql.class,method = "queryAgeByName")
	public Integer providerQueryAgeByName(String name);
	
	//删除测试数据
	@Delete("delete from test_mybatis_user where name = #{name}")
	public void delete(@Param("name") String name);
	
	//测试插入自增主键行后，如何获取自增主键值
	@Insert("insert into test_mybatis_user(name,age) value(#{name},#{age})")
	@Options(useGeneratedKeys = true,keyProperty ="id", keyColumn = "id")
	public Integer annotationInsert(User user);
	
	@Options(useGeneratedKeys = true,keyProperty ="id", keyColumn = "id")
	@InsertProvider(type = UserSql.class,method = "providerInsert")
	public Integer providerInsert(User user);
	
	public Integer xmlInsert(User user);
}
