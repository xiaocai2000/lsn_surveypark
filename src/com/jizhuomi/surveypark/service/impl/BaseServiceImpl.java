package com.jizhuomi.surveypark.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.jizhuomi.surveypark.dao.BaseDao;
import com.jizhuomi.surveypark.service.BaseService;

/**
 * 抽象的BaseService，专门用于继承
 * @author user
 *
 * @param <T>
 */
public class BaseServiceImpl<T> implements BaseService<T> {
	private BaseDao<T> dao;

	public BaseDao<T> getDao() {
		return dao;
	}

	@Resource
	public void setDao(BaseDao<T> dao) {
		this.dao = dao;
	}

	@Transactional
	@Override
	public void saveEntity(T t) {
		// TODO Auto-generated method stub
		dao.saveEntity(t);
	}

	@Override
	public void saveOrUpdateEntity(T t) {
		// TODO Auto-generated method stub
		dao.saveOrUpdateEntity(t);
	}

	@Override
	public void updateEntity(T t) {
		// TODO Auto-generated method stub
		dao.updateEntity(t);
	}

	@Override
	public void deleteEntity(T t) {
		// TODO Auto-generated method stub
		dao.deleteEntity(t);
	}

	@Override
	public void batchEntityByHQL(String hql, Object... objects) {
		// TODO Auto-generated method stub
		dao.batchEntityByHQL(hql, objects);
	}

	@Override
	public T loadEntity(Integer id) {
		// TODO Auto-generated method stub
		return dao.loadEntity(id);
	}

	@Override
	public T getEntity(Integer id) {
		// TODO Auto-generated method stub
		return dao.getEntity(id);
	}

	@Override
	public List<T> findEntityByHQL(String hql, Object... objects) {
		// TODO Auto-generated method stub
		return dao.findEntityByHQL(hql, objects);
	}

	@Override
	public Object uniqueResult(String hql, Object... objects) {
		// TODO Auto-generated method stub
		return dao.uniqueResult(hql, objects);
	}

}
