package com.lanxuan.pub.base.service;

import java.io.Serializable;

import org.springframework.transaction.annotation.Transactional;

import com.lanxuan.pub.base.dao.HibernateDao;
@Transactional
public abstract class EntityManager<T, PK extends Serializable> {

	protected abstract HibernateDao<T, PK> getEntityDao();
	
	@Transactional
	public T get(PK id){
		return  (T) getEntityDao().get(id);
	}
	
	
}
