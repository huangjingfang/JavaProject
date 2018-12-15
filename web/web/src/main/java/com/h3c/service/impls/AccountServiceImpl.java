package com.h3c.service.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.h3c.service.base.models.User;
import com.h3c.service.dao.interfaces.IUserDao;
import com.h3c.service.interfaces.IAccountService;

@Service("AccountService")
@Transactional
public class AccountServiceImpl implements IAccountService{

	@Autowired
	@Qualifier("UserDao")
	private IUserDao dao;
	
	public AccountServiceImpl(@Qualifier("UserDao")IUserDao dao) {
		// TODO Auto-generated constructor stub
	}
	
	public String sayHello(String name) {
		// TODO Auto-generated method stub
		return "hello "+ name;
	}

	public void save(User user) {
		// TODO Auto-generated method stub
		dao.save(user);
	}

}
