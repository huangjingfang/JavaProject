package com.h3c;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.h3c.base.models.User;
import com.h3c.base.utils.GetSpringBeanUtils;
import com.h3c.services.dao.interfaces.IUserDao;
import com.h3c.services.interfaces.IAccountService;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:spring-config.xml");
		IAccountService service = ac.getBean(IAccountService.class);
		User user = new User();
		user.setPassword("123456");
		user.setUserName("kolgan");
		service.save(user);
	}

}
