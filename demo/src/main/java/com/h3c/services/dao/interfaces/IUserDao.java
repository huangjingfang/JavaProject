package com.h3c.services.dao.interfaces;

import com.h3c.base.models.User;

public interface IUserDao {
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
