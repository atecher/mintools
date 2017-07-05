package com.atecher.mintools.service;


import com.atecher.mintools.model.Page;

import java.util.List;
import java.util.Map;

public interface IGenericService{
	
	<T> T getOne(String sql_key, Object object);
	
	<T> List<T> selectList(String sql_key, Object parameter);
	
	<T> Page<T> selectForPage(String sql_key, int pageNo, int limit, Map<String, Object> parameter);
	
	int update(String sql_key, Object parameter);
	
	Object insert(String sql_key, Object parameter);
	
	int delete(String sql_key, Object parameter);
}
