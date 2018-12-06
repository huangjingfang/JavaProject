package com.h3c.services.dao.interfaces;

import com.h3c.base.models.User;

public interface IUserDao extends IEntityDao<User>{
	/**
	 * Ôö
	 * @param user
	 */
	public void save(User user);
	
	/**
	 * ¸Ä
	 * @param user
	 */
	public void update(User user);
	
	/**
	 * É¾
	 * @param userName
	 */
	public void delete(User user);
}
