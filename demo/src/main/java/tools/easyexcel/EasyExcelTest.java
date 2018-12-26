package tools.easyexcel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;

public class EasyExcelTest {
	private static final String PATH = "C:\\Users\\h15039.H3C\\Desktop\\export.xlsx";
	private static final String POIPATH = "C:\\Users\\h15039.H3C\\Desktop\\exportpoi.xlsx";
	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
//		easyExcelTest();
		poiTest();
		Thread.sleep(1000*60*10);
	}
	
	private static void poiTest() throws FileNotFoundException {
		XSSFWorkbook book = new XSSFWorkbook();
		XSSFSheet sheet = book.createSheet("第一个sheet");
		OutputStream out = new FileOutputStream(new File(POIPATH));
		for(int i=0;i<1000000;i++) {
			XSSFRow row = sheet.createRow(i);
			XSSFCell c1 = row.createCell(0);
			c1.setCellValue("杭州");
			
			XSSFCell c2 = row.createCell(1);
			c2.setCellValue("900万人");
			
			XSSFCell c3 = row.createCell(2);
			c3.setCellValue("1.2万平方公里");
			
			XSSFCell c4 = row.createCell(3);
			c4.setCellValue("浙江省");
		}
		try {
			book.write(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private static void easyExcelTest() throws FileNotFoundException {
		OutputStream out = new FileOutputStream(new File(PATH));
		try {
			ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
			// 写第一个sheet, sheet1 数据全是List<String> 无模型映射关系
			Sheet sheet1 = new Sheet(1, 0,Model.class);
			sheet1.setSheetName("第一个sheet");
			writer.write(getListString(), sheet1);
			writer.finish();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static List<Model> getListString(){
		List<Model> model = new ArrayList<Model>();
		for(int i=0;i<1000000;i++) {
			model.add(new Model("杭州","900万人","1.2万平方公里","浙江省"));
		}
		return model;
	}
	
}
