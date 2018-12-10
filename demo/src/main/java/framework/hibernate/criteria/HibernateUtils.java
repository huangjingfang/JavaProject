package framework.hibernate.criteria;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate5.HibernateTemplate;

@SuppressWarnings("resource")
public class HibernateUtils {
	private static SessionFactory sessionFactory;
	private static HibernateTemplate hibernateTemplate;
	
	static {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-config.xml");
		sessionFactory = (SessionFactory) applicationContext.getBean("sessionFactory");
		hibernateTemplate = (HibernateTemplate) applicationContext.getBean("hibernateTemplate");
	}
	
	public static void setConfig(String classpathUrl) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(classpathUrl);
		sessionFactory = (SessionFactory) applicationContext.getBean("sessionFactory");
		hibernateTemplate = (HibernateTemplate) applicationContext.getBean("hibernateTemplate");
	}
	
	public static Session getSession() {
		return sessionFactory.openSession();
	}
	
	public static DetachedCriteria getDetachedCriteria(Class clazz) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(clazz);
		return detachedCriteria;
	}
	public static DetachedCriteria getDetachedCriteria(String entityName) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forEntityName(entityName);
		return detachedCriteria;
	}
	public static List findByDetachedCriteria(DetachedCriteria detachedCriteria) {
		return hibernateTemplate.findByCriteria(detachedCriteria);
	}
}
