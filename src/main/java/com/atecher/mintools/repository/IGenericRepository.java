package com.atecher.mintools.repository;

import java.util.List;

/**
 * 描述：mybatis数据库操作基类接口，提供基本的新增、修改、删除、查询等语句
 * @author 韩红伟
 * @version    v1.0
 */
public interface IGenericRepository{
	
	<T> T getOne(String sql_key, Object obj);
	
	<T> List<T> selectList(String sql_key, Object parameter);
	
	Integer getListCount(String sql_key, Object parameter);
	
	int update(String sql_key, Object parameter);
	
	Object insert(String sql_key, Object parameter);
	
	int delete(String sql_key, Object parameter);
	
}
