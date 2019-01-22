package top.yansite.springboot.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class BeanUtils implements ApplicationContextAware{
	private static ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		BeanUtils.applicationContext = applicationContext;
	}
	
	public static Object getBean(String name) {
		return applicationContext.getBean(name);
	}
	
	public static <T> T getBeanByClass(Class<T> clazz) {
		return applicationContext.getBean(clazz);
	}
}
