package com.jizhuomi.surveypark.dao;

import java.util.List;

/**
 * BaseDao接口
 */
public interface BaseDao<T> {
	// 写操作
	public void saveEntity(T t);
	public void saveOrUpdateEntity(T t);
	public void updateEntity(T t);
	public void deleteEntity(T t);
	public void batchEntityByHQL(String hql, Object...objects);
	
	// 读操作
	public T loadEntity(Integer id);
	public T getEntity(Integer id);
	public List<T> findEntityByHQL(String hql, Object...objects);
	public Object uniqueResult(String hql, Object...objects);
}
