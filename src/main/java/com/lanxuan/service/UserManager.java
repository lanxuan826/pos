package com.lanxuan.service;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanxuan.pub.base.dao.HibernateDao;
import com.lanxuan.pub.base.service.EntityManager;
import com.lanxuan.pub.entity.system.User;

@Service
public class UserManager  extends EntityManager<User, Long>{

	private SessionFactory sessionFactory;
	private HibernateDao<User, Long> userDao;
	
	protected HibernateDao<User, Long> getEntityDao() {
		return this.userDao;
	}

	public Session getSession(){
		return this.userDao.getSession();
	}
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory  = sessionFactory;
		this.userDao = new HibernateDao(this.sessionFactory, User.class);
	}
	
	public User getUserById(Long id){
		return (User) userDao.get(id);
	}
}
