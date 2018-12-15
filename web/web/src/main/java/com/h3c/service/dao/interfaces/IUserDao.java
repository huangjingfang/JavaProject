package com.h3c.service.dao.interfaces;

import com.h3c.service.base.models.User;

public interface IUserDao extends IEntityDao<User>{
	/**
	 * ��
	 * @param user
	 */
	public void save(User user);
	
	/**
	 * ��
	 * @param user
	 */
	public void update(User user);
	
	/**
	 * ɾ
	 * @param userName
	 */
	public void delete(User user);
}
