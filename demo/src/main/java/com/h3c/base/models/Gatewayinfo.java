package com.h3c.base.models;

import java.io.Serializable;
import java.util.Calendar;

public class Gatewayinfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2446210348064614510L;
	private Integer id;
	private String gwSn;
	private String province;
	private String city;
	private String county;
	private Calendar onlineTime;
	private String productName;
	private String ip;
	private String carrier;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGwSn() {
		return gwSn;
	}
	public void setGwSn(String gwSn) {
		this.gwSn = gwSn;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public Calendar getOnlineTime() {
		return onlineTime;
	}
	public void setOnlineTime(Calendar onlineTime) {
		this.onlineTime = onlineTime;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
}
