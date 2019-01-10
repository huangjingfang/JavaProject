package com.h3c.web.properties;

import java.util.Properties;

public class MailProperties {
	// 邮件设置
	public static String MAIL_RECEIEVERS = "kolgan_hjf@163.com";
	public static String MAIL_SENDER = "515195215@qq.com";
	public static String SENDER_PASSWD = "mphuglytmivvbhhf";
	public static String SMTP_SERVER = "smtp.qq.com";
	public static String SMTP_PORT = "465";

	public static void initConfig(Properties props) {
		MAIL_RECEIEVERS = props.getProperty("mail_receievers",MAIL_RECEIEVERS);
		MAIL_SENDER = props.getProperty("mail_sender",MAIL_SENDER);
		SENDER_PASSWD = props.getProperty("sender_passwd", SENDER_PASSWD);
		SMTP_PORT = props.getProperty("smtp_port",SMTP_PORT);
		SMTP_SERVER = props.getProperty("smtp_server",SMTP_SERVER);
	}
}
