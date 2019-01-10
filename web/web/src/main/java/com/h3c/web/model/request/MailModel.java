package com.h3c.web.model.request;

import java.io.Serializable;

public class MailModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2675495120060805939L;
	private String receiever;
	private String content;
	private String title;
	
	public MailModel() {
		// TODO Auto-generated constructor stub
	}
	
	public String getReceiever() {
		return receiever;
	}
	public void setReceiever(String receiever) {
		this.receiever = receiever;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
