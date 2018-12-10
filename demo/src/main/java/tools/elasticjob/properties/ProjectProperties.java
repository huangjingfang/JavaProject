package tools.elasticjob.properties;

import java.io.IOException;
import java.util.Properties;

public class ProjectProperties {
	private static Properties properties;
	
	public ProjectProperties() {
		// TODO Auto-generated constructor stub
		properties = new Properties();
		try {
			properties.load(getClass().getResourceAsStream("/conf/job.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String get(String name) {
		return properties.getProperty(name);
	}
	
//	public static void main(String[] args) {
//		ProjectProperties projectProperties = new ProjectProperties();
//		System.out.println(projectProperties.get("simple.id"));;
//	}
}	
