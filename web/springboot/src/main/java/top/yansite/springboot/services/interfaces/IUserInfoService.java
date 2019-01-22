package top.yansite.springboot.services.interfaces;

import top.yansite.springboot.model.UserInfo;

public interface IUserInfoService {

	public UserInfo getById(Integer id);
	
	public UserInfo getByName(String name);
}
