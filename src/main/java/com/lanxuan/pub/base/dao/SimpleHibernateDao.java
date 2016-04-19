package com.lanxuan.pub.base.dao;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.lanxuan.pub.utils.ReflectionUtils;


public class SimpleHibernateDao<T, PK extends Serializable> {
	
	protected SessionFactory sessionFactory;
	protected Class<T> entityClass;
	
	public SimpleHibernateDao() {
		this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
	}

	public SimpleHibernateDao(SessionFactory sessionFactory,
			Class<T> entityClass) {
		this.sessionFactory = sessionFactory;
		this.entityClass = entityClass;
	}
	
	public SessionFactory getSessionFactory(){
		return this.sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession(){
		return this.sessionFactory.getCurrentSession();
	}
	public T get(PK id){
		Assert.notNull(id, "id²»ÄÜÎª¿Õ");
		return (T) getSession().get(this.entityClass, id);
	}
	
	
}
