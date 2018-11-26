package com.h3c.services.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.h3c.base.models.User;
import com.h3c.services.dao.interfaces.IUserDao;

@Repository("UserDao")
public class UserDao implements IUserDao{
//	@Autowired
//	@Qualifier("sessionFactory")
//	protected SessionFactory sessionFactory;
	
//	@Autowired
//	@Qualifier("hibernateTemplate")
//	protected HibernateTemplate hibernateTemplate;

	public void save(User user) {
		// TODO Auto-generated method stub
		
	}

	public void update(User user) {
		// TODO Auto-generated method stub
		
	}

	public void delete(String userName) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
