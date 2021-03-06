package db.mysql.largescalequery;

import java.util.List;

import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StreamFetch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings({ "unused", "resource" })
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-config.xml");
		SessionFactory sessionFactory = (SessionFactory) applicationContext.getBean("sessionFactory");
		Session session = sessionFactory.openSession();
		Query<Object> query = session.createQuery("select id,gwSn,province,city,county,onlineTime,productName,ip,carrier from Gatewayinfo");
		//select Id,gwSn,Province,City,County,OnlinTime,ProductName,IP,Carrier from gwrelation
		
		scallableResultNew(query, session);
//		parallelStream(query);
//		list(query);
//		scallableResult(query);
		
		session.close();
		sessionFactory.close();
	}

	public static void parallelStream(Query<Object> query) {
		query.stream().parallel().forEach(StreamFetch::print);
	}
	
	public static void list(Query<Object> query) {
		List<Object> list = query.list();
		for(Object obj:list) {
			print(obj);
		}
	}
	
	public static void scallableResult(Query<Object> query) {
		query.scroll(ScrollMode.FORWARD_ONLY);
		ScrollableResults results = query.scroll();
		while(results.next()) {
			String sn = results.getString(1);
			String province = results.getString(2);
			String city = results.getString(3);
			String county = results.getString(4);
			System.out.println(sn+"\t"+province+"\t"+city+"\t"+county);
		}
	}
	
	public static void scallableResultNew(Query<Object> query,Session session) {
		query.scroll(ScrollMode.FORWARD_ONLY);
		query.setFetchSize(1000).setCacheable(false);
		
		ScrollableResults results = query.scroll();
		while(results.next()) {
			String sn = results.getString(1);
			String province = results.getString(2);
			String city = results.getString(3);
			String county = results.getString(4);
			System.out.println(sn+"\t"+province+"\t"+city+"\t"+county);
		}
	}
	
	public static void log(Object obj) {
		Object[] objs = (Object[]) obj;
		StringBuilder builder = new StringBuilder();
		for(Object o:objs) {
			if(o==null) {
				builder.append((String)null).append("\t");
			}else {
				builder.append(o.getClass().getName()).append("\t");
			}
		}
		System.out.println(builder.toString());
	}
	
	public static void print(Object obj) {
		Object[] objs = (Object[]) obj;
		StringBuilder builder = new StringBuilder();
		String sn = (String) objs[1];
		builder.append(sn).append("\t");
		String province = (String) objs[2];
		builder.append(province).append("\t");
		String city = (String) objs[3];
		builder.append(city).append("\t");
		String county = (String) objs[4];
		builder.append(county).append("\t");
		System.out.println(builder.toString());
	}
}
