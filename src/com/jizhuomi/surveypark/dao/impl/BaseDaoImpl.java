package com.jizhuomi.surveypark.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.jizhuomi.surveypark.dao.BaseDao;
import com.jizhuomi.surveypark.model.Survey;

/**
 * 抽象的dao实现，专门用于继承
 */
public abstract class BaseDaoImpl<T> implements BaseDao<T> {
	@Resource
	private SessionFactory sf;
	private Class<T> clazz;
	
	public BaseDaoImpl() {
		// 得到泛型化超类
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class<T>) type.getActualTypeArguments()[0];
	}

	@Override
	public void saveEntity(T t) {
		// TODO Auto-generated method stub
		sf.getCurrentSession().save(t);
	}

	@Override
	public void saveOrUpdateEntity(T t) {
		// TODO Auto-generated method stub
		sf.getCurrentSession().saveOrUpdate(t);
	}

	@Override
	public void updateEntity(T t) {
		// TODO Auto-generated method stub
		sf.getCurrentSession().update(t);
	}

	@Override
	public void deleteEntity(T t) {
		// TODO Auto-generated method stub
		sf.getCurrentSession().delete(t);
	}

	/**
	 * 按照HQL语句进行批量更新
	 */
	@Override
	public void batchEntityByHQL(String hql, Object... objects) {
		// TODO Auto-generated method stub
		Query q = sf.getCurrentSession().createQuery(hql);
		for (int i = 0; i < objects.length; i ++) {
			q.setParameter(i, objects[i]);
		}
		q.executeUpdate();
	}

	@Override
	public T loadEntity(Integer id) {
		// TODO Auto-generated method stub
		return sf.getCurrentSession().load(clazz, id);
	}

	@Override
	public T getEntity(Integer id) {
		// TODO Auto-generated method stub
		return sf.getCurrentSession().get(clazz, id);
	}

	@Override
	public List<T> findEntityByHQL(String hql, Object... objects) {
		// TODO Auto-generated method stub
		Query q = sf.getCurrentSession().createQuery(hql);
		for (int i = 0; i < objects.length; i ++) {
			q.setParameter(i, objects[i]);
		}
		return q.list();
	}

}
