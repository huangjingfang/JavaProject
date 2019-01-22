package top.yansite.springboot.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.yansite.springboot.dao.UserDao;
import top.yansite.springboot.model.UserInfo;
import top.yansite.springboot.services.interfaces.IUserInfoService;

@Service
public class UserInfoServiceImpl implements IUserInfoService{

	@Autowired
	UserDao userDao;

	@Override
	public UserInfo getById(Integer id) {
		// TODO Auto-generated method stub
		return userDao.getById(id);
	}

	@Override
	public UserInfo getByName(String name) {
		// TODO Auto-generated method stub
		return userDao.getByName(name);
	}
	
}
