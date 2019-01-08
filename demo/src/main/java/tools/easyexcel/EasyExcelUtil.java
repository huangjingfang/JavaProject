package tools.easyexcel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.xssf.streaming.SXSSFFormulaEvaluator.SheetsFlushedException;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.TableStyle;
import com.alibaba.excel.support.ExcelTypeEnum;

public class EasyExcelUtil {
	private static final int MAX_ROW_SIZE = 1000000;
	private ExcelWriter writer;
	private int pageIndex;
	private int rowIndex;
	private String sheetName;
	private Sheet currentSheet;
	private Class<? extends BaseRowModel> exportClass;
	
	public EasyExcelUtil() {
		// TODO Auto-generated constructor stub
		pageIndex = 0;
		rowIndex = 0;
	}
	
	/**
	 * 自定义表头，此方法未对过大长度的content进行适配。如果用此方法导出大量content内容，请适配该方法
	 * @param sheetName
	 * @param header
	 * @param content
	 * @throws Exception
	 */
	public void export(String sheetName,List<String> header,List<List<String>> content) throws Exception {
		if(writer==null) {
			throw new Exception("未配置输出流！");
		}
		Sheet sheet = new Sheet(pageIndex);
		sheet.setSheetName(sheetName);
		sheet.setHead(adapt(header));
		
		writer.write0(content, sheet);
		pageIndex++;
	}
	
	/**
	 * 后台导出接口，后台分批从数据库中取数据，然后导入Excel，成功之后将数据扔到OSS上，并把OSS的地址返回给前台，前台重定向到OSS地址来下载Excel
	 * 
	 * 无数据限制，内存消耗极低，推荐使用该方式
	 * @param writer
	 * @throws Exception 
	 */
	public void export(List<? extends BaseRowModel> list) throws Exception {
		if(writer==null) {
			throw new Exception("未配置输出流！");
		}
		if(sheetName == null) {
			throw new Exception("未配置表单名字！");
		}
		if(exportClass == null) {
			throw new Exception("未配置导出类");
		}
		
		if(currentSheet == null) {
			currentSheet = new Sheet(pageIndex, 0, exportClass);
			currentSheet.setSheetName(sheetName);
		}
		int size = list.size();
		if(rowIndex+size<=MAX_ROW_SIZE) {
			writer.write(list, currentSheet);
			rowIndex += size;
		}else {
			int thisPageSize = MAX_ROW_SIZE - rowIndex;
			List<? extends BaseRowModel> thisPage = list.subList(0, thisPageSize);
			writer.write(thisPage, currentSheet);
			pageIndex++;
			currentSheet = new Sheet(pageIndex, 0, exportClass);
			currentSheet.setSheetName(sheetName+"("+pageIndex+")");
			rowIndex = 0;
			List<? extends BaseRowModel> nextPage = list.subList(thisPageSize, list.size());
			export(nextPage);
		}
	}
	
	public void addSheet(String sheetName,List<? extends BaseRowModel> data,Class<? extends BaseRowModel> clazz,int sheetIndex) {
		Sheet sheet = new Sheet(sheetIndex, 0, clazz);
		sheet.setSheetName(sheetName);
		writer.write(data, sheet);
		pageIndex++;
	}
	
	public void finish() {
		if(writer==null) {
			return;
		}
		writer.finish();
	}
	public EasyExcelUtil setOutPutStream(OutputStream out) {
		writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
		return this;
	}
	
	public EasyExcelUtil setSheetName(String sheetName){
		this.sheetName = sheetName;
		return this;
	}
	
	public EasyExcelUtil setExportName(Class<? extends BaseRowModel> clazz) {
		exportClass = clazz;
		return this;
	}
	
	public static void main(String[] args) throws Exception {
		EasyExcelUtil util = new EasyExcelUtil();
		OutputStream out = new FileOutputStream(new File("C:\\Users\\h15039.H3C\\Desktop\\exportResultNew1.xlsx"));
		util.setOutPutStream(out).setExportName(Gwrelation.class).setSheetName("地市信息");
		BufferedReader reader = new BufferedReader(new FileReader(new File("C:\\Users\\h15039.H3C\\Desktop\\result.txt")));
		List<Gwrelation> list = new ArrayList<Gwrelation>();
		util.addSheet("begin", new ArrayList<Model>(), Model.class, 0);
		while(reader.ready()) {
			String line = reader.readLine();
			Gwrelation gwrelation = new Gwrelation(line);
			list.add(gwrelation);
			if(list.size()==8000) {
				util.export(list);
				list.clear();
				System.out.println(util.rowIndex);
			}
		}
		reader.close();
		util.finish();
	}
	
	public static void test() throws Exception {
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
	
	
	private List<List<String>> adapt(List<String> list){
		List<List<String>> data = new ArrayList<>();
		for(String str:list) {
			data.add(Arrays.asList(str));
		}
		return data;
	}
}
