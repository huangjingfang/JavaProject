package db.mysql.largescalequery;

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
		
		query.scroll(ScrollMode.FORWARD_ONLY);
		ScrollableResults results = query.scroll();
		while(results.next()) {
			String sn = results.getString(1);
			String province = results.getString(2);
			String city = results.getString(3);
			String county = results.getString(4);
			System.out.println(sn+"\t"+province+"\t"+city+"\t"+county);
		}
		query.stream();
		
		session.close();
		sessionFactory.close();
	}

}
