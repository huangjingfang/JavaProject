package com.h3c.web.system;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.h3c.web.properties.MailProperties;

public class ProjectInit implements ApplicationListener<ContextRefreshedEvent> {
	private static final Logger logger = LoggerFactory.getLogger(ProjectInit.class);
	
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		if(event.getApplicationContext().getParent()==null) {
			initData();
		}
	}
	
	private void initData() {
		System.out.println("-------------------init project-----------------");
		Properties properties = new Properties();
		InputStream inputStream = getClass().getResourceAsStream("/mail.properties");
		try {
			properties.load(inputStream);
			MailProperties.initConfig(properties);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("找不到邮件配置", e);
		}
		
	}

}
