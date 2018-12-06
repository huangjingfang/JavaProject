package com.h3c.services.dao.impl;

import org.springframework.stereotype.Repository;

import com.h3c.base.models.UserSign;
import com.h3c.services.dao.interfaces.IUserSignDao;

@Repository("UserSignDao")
public class UserSignDao extends EntityDao<UserSign> implements IUserSignDao {

}
