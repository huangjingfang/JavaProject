package jlan.lab.anotation;

import java.lang.reflect.Method;
import java.util.Date;

public class LogParser {
	public static void main(String[] args) {
		LogParser.parse(LogParser.class);
		LogParser parser = new LogParser();
//		parser.testMethod();
	}
	
	@Log(msg="testMethod",date="2018-12-06")
	public void testMethod() {
		System.out.println("testMethod");
	}
	
	public static void parse(Class clazz) {
		Method[] methods = clazz.getDeclaredMethods();
		for(Method method: methods) {
			Log log = method.getAnnotation(Log.class);
			if(log!=null) {
				System.out.println(new Date().toString()+":"+log.msg()+"\t"+log.date());
			}
		}
	}
}
