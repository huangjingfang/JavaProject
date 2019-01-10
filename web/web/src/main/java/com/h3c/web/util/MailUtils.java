package com.h3c.web.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.h3c.web.properties.MailProperties;

public class MailUtils {
	public static void sendMail(String title,String content) throws Exception {
		Properties props = new Properties();
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.host", MailProperties.SMTP_SERVER);
		props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", MailProperties.SMTP_PORT);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.socketFactory.port", MailProperties.SMTP_PORT);
        props.put("mail.smtp.starttls.enable", "true");
        
		Session session = Session.getInstance(props);
        session.setDebug(true);
		
		MimeMessage message = createMimeMessage(session, MailProperties.MAIL_SENDER , MailProperties.MAIL_RECEIEVERS,title,content);
		
		Transport transport = session.getTransport();
		transport.connect(null, MailProperties.MAIL_SENDER, MailProperties.SENDER_PASSWD);
		transport.sendMessage(message, message.getAllRecipients());
		
		transport.close();
	}
	
	private static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail,String title,String content) throws Exception {
		MimeMessage message = new MimeMessage(session);
		//1.设置发件人
		message.setFrom(new InternetAddress(sendMail, "JF Huang", "UTF-8"));
		//2.收件人
		String[] reveieves = receiveMail.split(",",-1);
		if(reveieves.length==1) {
			message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(reveieves[0], "Kolgan Huang", "UTF-8"));
		}else {
			message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(reveieves[0], "Kolgan Huang", "UTF-8"));
			for(int i=1;i<reveieves.length;i++) {
				message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(reveieves[i], "Kolgan Huang", "UTF-8"));
			}
		}
		
		message.setSubject(title,"UTF-8");
		message.setContent(content, "text/html;charset=UTF-8");
		message.setSentDate(new Date());
		message.saveChanges();
		
		return message;
	}
	
	public static void sendMail(String receiever,String title,String content) throws Exception {
		Properties props = new Properties();
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.host", MailProperties.SMTP_SERVER);
		props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", MailProperties.SMTP_PORT);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.socketFactory.port", MailProperties.SMTP_PORT);
        props.put("mail.smtp.starttls.enable", "true");
        
		Session session = Session.getInstance(props);
        session.setDebug(true);
		
		MimeMessage message = createMimeMessage(session, MailProperties.MAIL_SENDER , receiever,title,content);
		
		Transport transport = session.getTransport();
		transport.connect(null,MailProperties.MAIL_SENDER, MailProperties.SENDER_PASSWD);
		transport.sendMessage(message, message.getAllRecipients());
		
		transport.close();
	}
	
	public static void sendMail(Exception e,String title){
		StringBuilder builder = new StringBuilder();
		StackTraceElement[] elements = e.getStackTrace();
		for(StackTraceElement element:elements) {
			builder.append(element.toString()).append("<br/>");
		}
		String content = builder.toString();
		try {
			sendMail(title,content);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public static void sendMail(Exception e,String title,String cont){
		StringBuilder builder = new StringBuilder(cont).append("<br/>");
		StackTraceElement[] elements = e.getStackTrace();
		for(StackTraceElement element:elements) {
			builder.append(element.toString()).append("<br/>");
		}
		String content = builder.toString();
		try {
			sendMail(title,content);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
