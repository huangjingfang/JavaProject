package tools.easyexcel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;

public class EasyExcelUtil<T extends BaseRowModel> {
	private ExcelWriter writer;
	private int pageIndex;
	private int rowIndex;
	private String sheetName;
	private Sheet currentSheet;
	private Class<T> paramClass;
	
	@SuppressWarnings("unchecked")
	public EasyExcelUtil() {
		// TODO Auto-generated constructor stub
		pageIndex = 0;
		rowIndex = 0;
		Type genericSuperClass = this.getClass().getGenericSuperclass();
		paramClass = (Class<T>) ((ParameterizedType)genericSuperClass).getActualTypeArguments()[1];
	}
	/**
	 * 后台导出接口，后台分批从数据库中取数据，然后导入Excel，成功之后将数据扔到OSS上，并把OSS的地址返回给前台，前台重定向到OSS地址来下载Excel
	 * @param writer
	 * @throws Exception 
	 */
	public void export(List<String> list) throws Exception {
		if(writer==null) {
			throw new Exception("未配置输出流！");
		}
		if(sheetName == null) {
			throw new Exception("未配置表单名字！");
		}
		if(currentSheet == null) {
			currentSheet = new Sheet(pageIndex, 0, paramClass);
		}
	}
	
	public EasyExcelUtil<T> setOutPutStream(OutputStream out) {
		writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
		return this;
	}
	
	public EasyExcelUtil<T> setSheetName(String sheetName){
		this.sheetName = sheetName;
		return this;
	}
	
	public static void main(String[] args) throws IOException {
		EasyExcelUtil<Gwrelation> util = new EasyExcelUtil<Gwrelation>();
		System.out.println(util.paramClass.getName());
	}
	
	private static void test() throws Exception {
		OutputStream out = new FileOutputStream(new File("C:\\Users\\h15039.H3C\\Desktop\\exportResult.xlsx"));
		String sheetName = "地市信息";
		BufferedReader reader = new BufferedReader(new FileReader(new File("C:\\Users\\h15039.H3C\\Desktop\\result.txt")));
		List<Gwrelation> list = new ArrayList<>();
		while(reader.ready()) {
			String line = reader.readLine();
			Gwrelation gwrelation = new Gwrelation(line);
			list.add(gwrelation);
		}
		reader.close();
		try {
//			simpleExport(out, sheetName, Gwrelation.class, list);
			simpleExportMulti(out, sheetName, Gwrelation.class, list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 简单导出Excel，使用默认格式，每个文件只包含一个表格
	 * 
	 * @param out excel的输出流
	 * @param sheetName 表单的名字
	 * @param clazz Excel的映射文件的类对象
	 * @param list 数据
	 * @throws Exception 
	 */
	public static void simpleExport(OutputStream out,String sheetName,Class<? extends BaseRowModel> clazz,List<? extends BaseRowModel> list) throws Exception {
		if(list.size()>1048576) {
			throw new Exception("数据量大于excel最大行数1048576！");
		}
		ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
		Sheet sheet1 = new Sheet(1, 0,clazz);
		sheet1.setSheetName(sheetName);
		writer.write(list, sheet1);
		writer.finish();
	}
	
	/**
	 * 通用导出Excel，条数无限制，每页最多100万数据，默认样式。单是依然不建议使用该方法，理由是一个list可以占用上百MB的内存，遇到多个并发也会出现问题
	 * 
	 * simpleExport系列接口都只适用于前台导出。
	 * @param out
	 * @param sheetName
	 * @param clazz
	 * @param list
	 * @throws Exception
	 */
	public static void simpleExportMulti(OutputStream out,String sheetName,Class<? extends BaseRowModel> clazz,List<? extends BaseRowModel> list) throws Exception {
		ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
		for(int i=0;i*1000000<list.size();i++) {
			int toIndex = (i+1)*1000000>list.size()?list.size():(i+1)*1000000;
			List<? extends BaseRowModel> sublist = list.subList(i*1000000, toIndex);
			Sheet sheet = new Sheet(i, 0,clazz);
			sheet.setSheetName(sheetName+"("+(i+1)+")");
			writer.write(sublist, sheet);
		}
		writer.finish();
	}
}
