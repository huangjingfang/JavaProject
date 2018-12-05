package com.h3c;

import java.util.Random;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.h3c.base.models.User;
import com.h3c.base.utils.GetSpringBeanUtils;
import com.h3c.services.dao.interfaces.IUserDao;
import com.h3c.services.interfaces.IAccountService;

public class Main {
	static final char[] aeiou = new char[] {'a','e','i','o','u','y'};
	static final char[] other = new char[] {'b','c','d','f','g','h','j','k','l','m','n','p','q','r','s','t','v','w','x','y','z'};
	static Random rand = new Random();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:spring-config.xml");
		IAccountService service = ac.getBean(IAccountService.class);
		for(int i=0;i<1000;i++) {
			User user = new User();
			user.setPassword(rand.nextInt(100000)+"");
			user.setUserName(getUserName(5+rand.nextInt(6)));
			service.save(user);
		}
	}
	
	public static String getUserName(int length) {
		int tick = 0;
		StringBuilder builder = new StringBuilder();
		boolean start = rand.nextBoolean();
		for(int i=0;i<length;i++) {
			if(start) {
				builder.append(other[rand.nextInt(other.length-1)]);
			}else {
				builder.append(aeiou[rand.nextInt(aeiou.length-1)]);
			}
			tick++;
			if(tick==2) {
				tick=0;
				start = !start;
			}
		}
		return builder.toString();
	}
}
