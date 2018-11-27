package com.h3c.services.interfaces;

import com.h3c.base.models.User;

public interface IAccountService {
	public String sayHello(String name);
	
	public void save(User user);
}
