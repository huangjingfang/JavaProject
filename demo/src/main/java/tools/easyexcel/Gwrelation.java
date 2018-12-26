package tools.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class Gwrelation extends BaseRowModel {
	@ExcelProperty(value="网关序列号",index=0)
	private String gwSn;
	@ExcelProperty(value="IP",index=1)
	private String ip;
	@ExcelProperty(value="国家",index=2)
	private String country;
	@ExcelProperty(value="省份",index=3)
	private String province;
	@ExcelProperty(value="地市",index=4)
	private String city;
	@ExcelProperty(value="运营商",index=5)
	private String carrier;
	@ExcelProperty(value="组织",index=6)
	private String organization;
	
	public Gwrelation(String line) {
		// TODO Auto-generated constructor stub
		String[] properties = line.split("\t",-1);
		if(properties.length!=7) {
			System.out.println(line);
		}else {
			gwSn = properties[0];
			ip = properties[1];
			country = properties[2];
			province = properties[3];
			city = properties[4];
			carrier = properties[5];
			organization = properties[6];
		}
	}
	
	public String getGwSn() {
		return gwSn;
	}
	public void setGwSn(String gwSn) {
		this.gwSn = gwSn;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
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
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
}
