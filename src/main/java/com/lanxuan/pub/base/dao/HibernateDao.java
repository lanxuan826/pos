package com.lanxuan.pub.base.dao;

import java.io.Serializable;

import org.hibernate.SessionFactory;

import com.lanxuan.entity.User;

public  class HibernateDao<T, PK extends Serializable> extends SimpleHibernateDao {

	
	public HibernateDao() {
		super();
	}

	public HibernateDao(SessionFactory sessionFactory, Class<T> entityClass) {
		super(sessionFactory, entityClass);
	}

	
	
	
}
