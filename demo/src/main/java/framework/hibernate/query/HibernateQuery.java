package framework.hibernate.query;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.h3c.base.models.Gatewayinfo;

public class HibernateQuery {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		byCriteria();
//		byNativeQuery();
//		byHQLQuery();
		byCriteriaQuery();
//		byDetachedCriteria();
	}
	
	/**
	 * Criteria has been deprecated since 5.2
	 */
	public static void byCriteria() {
		@SuppressWarnings("resource")
		ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:spring-config.xml");
		SessionFactory factory = (SessionFactory) ac.getBean("sessionFactory");
		
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Gatewayinfo.class);
		criteria.add(Restrictions.eq("province", "福建省"));
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.property("gwSn"));
		projectionList.add(Projections.property("city"));
		criteria.setProjection(projectionList);
		criteria.setMaxResults(1000);
		List<Object> list = criteria.list();
		for(Object info:list) {
			Object[] objs = (Object[]) info;
			for(Object obj:objs) {
				System.out.print(obj+"\t");
			}
			System.out.println();
		}
	}
	
	/**
	 * 使用原生的sql
	 */
	public static void byNativeQuery() {
		@SuppressWarnings("resource")
		ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:spring-config.xml");
		SessionFactory factory = (SessionFactory) ac.getBean("sessionFactory");
		
		Session session = factory.openSession();
		NativeQuery<Object> nativeQuery = session.createNativeQuery("select gwSn from gwrelation where Province = '福建省'");
		nativeQuery.setMaxResults(1000);
		
		System.out.println(nativeQuery.getQueryString());
		List<Object> list = nativeQuery.getResultList();
		for(Object info:list) {
			System.out.println(info);
		}
	}
	
	/**
	 * hql操作对象是映射过的对象，而不是数据表
	 */
	public static void byHQLQuery() {
		@SuppressWarnings("resource")
		ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:spring-config.xml");
		SessionFactory factory = (SessionFactory) ac.getBean("sessionFactory");
		
		Session session = factory.openSession();
		Query<Object> query = session.createQuery("select gwSn from Gatewayinfo where province = '福建省'");
		query.setMaxResults(1000);
//		query.setFirstResult(100000);
		List<Object> list = query.getResultList();
		for(Object info:list) {
			System.out.println(info);
		}
	}
	
	/**
	 * JPA的接口贼鸡儿难用，不懂为啥放弃Criteria的方式
	 */
	public static void byCriteriaQuery() {
		@SuppressWarnings("resource")
		ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:spring-config.xml");
		SessionFactory factory = (SessionFactory) ac.getBean("sessionFactory");
		
		Session session = factory.openSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Gatewayinfo> criteriaQuery = criteriaBuilder.createQuery(Gatewayinfo.class);
		Root<Gatewayinfo> root = criteriaQuery.from(Gatewayinfo.class);
		criteriaQuery.where(criteriaBuilder.equal(root.get("province"), "福建省"));
		Query<Gatewayinfo> query = session.createQuery(criteriaQuery);
		
		query.setMaxResults(1000);
		List<Gatewayinfo> list = query.getResultList();
		for(Object info:list) {
			System.out.println(info);
		}
	}
	
	/**
	 * by detachedCriteria
	 */
	public static void byDetachedCriteria() {
		@SuppressWarnings("resource")
		ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:spring-config.xml");
		SessionFactory factory = (SessionFactory) ac.getBean("sessionFactory");
		Session session = factory.openSession();
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Gatewayinfo.class);
		detachedCriteria.add(Restrictions.eq("province", "福建省"));
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.property("gwSn"));
		projectionList.add(Projections.property("city"));
		detachedCriteria.setProjection(projectionList);
		Criteria criteria = detachedCriteria.getExecutableCriteria(session);
		criteria.setMaxResults(1000);
		List<Object> list = criteria.list();
		for(Object info:list) {
			Object[] objs = (Object[]) info;
			for(Object obj:objs) {
				System.out.print(obj+"\t");
			}
			System.out.println();
		}
	}
}
