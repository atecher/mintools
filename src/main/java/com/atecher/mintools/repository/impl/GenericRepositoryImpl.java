package com.atecher.mintools.repository.impl;
import com.atecher.mintools.repository.IGenericRepository;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述：mybatis数据库操作基类接口，提供基本的新增、修改、删除、查询等语句
 * @author 韩红伟
 * @email hongwei.han@qq.com
 * @版本    v1.0
 */
@Repository
public class GenericRepositoryImpl implements IGenericRepository {
	@Autowired
	public SqlSessionTemplate sqlSession;
	
	@SuppressWarnings("unchecked")
	public<T> T getOne(String sql_key,Object obj){
		
		return (T)sqlSession.selectOne(sql_key, obj);
	}
	
	public <E> List<E> selectList(String sql_key, Object parameter) {
		return sqlSession.selectList(sql_key, parameter);
	}
	
	public Integer getListCount(String sql_key,Object parameter){
		return sqlSession.selectOne(sql_key, parameter);
	}
	
	public int update(String sql_key,Object parameter){
		return sqlSession.update(sql_key, parameter);
	}
	
	public Object insert(String sql_key,Object parameter){
		return sqlSession.insert(sql_key, parameter);
	}
	
	public int delete(String sql_key,Object parameter){
		return sqlSession.delete(sql_key, parameter);
	}

	
	
}
