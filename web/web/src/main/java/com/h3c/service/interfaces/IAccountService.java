package com.h3c.service.interfaces;

import com.h3c.service.base.models.User;

public interface IAccountService {
	public String sayHello(String name);
	
	public void save(User user);
}
