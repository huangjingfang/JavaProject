package top.yansite.springboot.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import top.yansite.springboot.model.UserInfo;

@Transactional
@Repository
public class UserDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public void save(UserInfo info) {
		getSession().save(info);
	}
	
	public UserInfo getById(Integer id) {
		Session session = getSession();
		Query<UserInfo> query = session.createQuery("from UserInfo where id ="+id);
		return query.uniqueResult();
	}
	
	public UserInfo getByName(String name) {
		Session session = getSession();
		Query<UserInfo> query = session.createQuery("from UserInfo where userName = '"+name+"'");
		List<UserInfo> list = query.list();
		if(list==null||list.size()==0) {
			return null;
		}
		return list.get(0);
	}
}
