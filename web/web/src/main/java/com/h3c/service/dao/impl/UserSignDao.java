package com.h3c.service.dao.impl;

import org.springframework.stereotype.Repository;

import com.h3c.service.base.models.UserSign;
import com.h3c.service.dao.interfaces.IUserSignDao;

@Repository("UserSignDao")
public class UserSignDao extends EntityDao<UserSign> implements IUserSignDao {

}
