package com.h3c.services.impl;

import org.springframework.stereotype.Service;

import com.h3c.services.interfaces.IAccountService;

@Service("AccountService")
public class AccountServiceImpl implements IAccountService{

	public String sayHello(String name) {
		// TODO Auto-generated method stub
		return "hello "+ name;
	}

}
