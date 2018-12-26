package tools.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class Model extends BaseRowModel{
	@ExcelProperty(value= "城市",index = 0)
	private String city;
	@ExcelProperty(value= "人口",index = 1)
	private String population;
	@ExcelProperty(value= "面积",index = 2)
	private String area;
	@ExcelProperty(value= "省份",index = 3)
	private String province;
	
	public Model(String city,String population,String area,String province) {
		// TODO Auto-generated constructor stub
		this.city = city;
		this.population = population;
		this.area = area;
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPopulation() {
		return population;
	}

	public void setPopulation(String population) {
		this.population = population;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
}