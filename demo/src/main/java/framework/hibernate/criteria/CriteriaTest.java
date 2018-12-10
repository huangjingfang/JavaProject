package framework.hibernate.criteria;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import com.google.gson.Gson;
import com.h3c.base.models.User;

/**
 * 
 * @author kolgan Huang
 * 
 *
 */
public class CriteriaTest {
	private static Gson gson = new Gson();
	
	public static void main(String[] args) {
//		detachedTest();
//		detatchedWithoutHibernateTamplate();
		testCriteriaQuery();
	}
	
	/**
	 * Criteria 的用法
	 */
	public static void criteriaTest() {
		Session session = HibernateUtils.getSession();
		Criteria criteria = session.createCriteria("com.h3c.base.models.User");
		criteria.add(Restrictions.lt("id", 1500));
		List<User> list = criteria.list();
		Gson gson = new Gson();
		for(User user:list) {
			System.out.println(gson.toJson(user));
		}
		session.close();
	}
	
	/**
	 * detachedCriteria的用法
	 */
	public static void detachedTest() {
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
	
	/**
	 * detachedCriteria 不使用hibernateTamplate的用法
	 */
	public static void detatchedWithoutHibernateTamplate() {
		DetachedCriteria detachedCriteria = HibernateUtils.getDetachedCriteria("com.h3c.base.models.User");
		detachedCriteria.add(Restrictions.le("id", 1500));
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.property("userName"));
		projectionList.add(Projections.property("password"));
		detachedCriteria.setProjection(projectionList);
		Session session = HibernateUtils.getSession();
		Criteria criteria = detachedCriteria.getExecutableCriteria(session);
		List<Object> list = criteria.list();
		Gson gson = new Gson();
		for(Object obj:list) {
			System.out.println(gson.toJson(obj));
		}
	}
	
	/**
	 * hibernate 5.0之后@Deprecated了Criteria的用法而推荐使用JPA规范的CriteriaQuery
	 * 该方法为CriteriaQuery的用法
	 */
	public static void testCriteriaQuery() {
		Session session = HibernateUtils.getSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		Root<User> root = criteriaQuery.from(User.class);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get("userName"), "hello"));
//		criteriaQuery.where(criteriaBuilder.lt(root.get(), 1500));
		
		Query<User> query = session.createQuery(criteriaQuery);
		List<User> list = query.list();
		for(User user:list) {
			System.out.println(gson.toJson(user));
		}
	}
}
