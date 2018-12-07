package jlan.hibernate.connectionpool;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.google.gson.Gson;

import jlan.hibernate.criteria.HibernateUtils;

public class DruidTest {
	public static void main(String[] args) {
		HibernateUtils.setConfig("classpath:spring-druid-config.xml");
		DetachedCriteria detachedCriteria = HibernateUtils.getDetachedCriteria("com.h3c.base.models.User");
		detachedCriteria.add(Restrictions.le("id", 1500));
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.property("userName"));
		projectionList.add(Projections.property("password"));
		detachedCriteria.setProjection(projectionList);
		List<Object> list = HibernateUtils.findByDetachedCriteria(detachedCriteria);
		Gson gson = new Gson();
		for(Object obj:list) {
			System.out.println(gson.toJson(obj));
		}
		
	}
}
