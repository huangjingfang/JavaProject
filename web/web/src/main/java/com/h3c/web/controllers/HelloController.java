package com.h3c.web.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.google.gson.Gson;
import com.h3c.easyexcel.excel.ExcelUtil;
import com.h3c.easyexcel.test.ExportInfo;
import com.h3c.service.base.models.User;
import com.h3c.service.base.utils.GetSpringBeanUtils;
import com.h3c.service.interfaces.IAccountService;
import com.h3c.web.model.request.MailModel;
import com.h3c.web.model.response.SingleResponse;
import com.h3c.web.util.MailUtils;

@Controller
@RequestMapping(value = "/hello")
public class HelloController {
	int a = 0;

	@ResponseBody
	@RequestMapping(value = "/sayHello", method = { RequestMethod.GET })
	public String sayHello(HttpServletRequest request, HttpServletResponse response, Model model) {
		a++;
		return "hello SpringMVC:" + a;
	}

	@ResponseBody
	@RequestMapping(value = "json", produces = "application/json; charset=UTF-8", method = { RequestMethod.GET })
	public String getJson() {
		List<String> list = new ArrayList<String>();
		list.add("apple");
		list.add("orange");
		list.add("nut");

		return new Gson().toJson(list);
	}

	@ResponseBody
	@RequestMapping(value = "addUser", produces = "application/json; charset=UTF-8", method = { RequestMethod.GET })
	public String addUser(String userName, String password) {
		IAccountService service = (IAccountService) GetSpringBeanUtils.getBean("AccountService");
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		service.save(user);
		Map<String, String> map = new HashMap<String, String>();
		map.put("success", "true");
		return new Gson().toJson(map);
	}

	@RequestMapping(value = "excel", produces = "multipart/form-data; charset=UTF-8", method = { RequestMethod.GET })
	public void cooperation(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String fileName = new String(("UserInfo " + new SimpleDateFormat("yyyy-MM-dd").format(new Date())).getBytes(),
				"UTF-8");
		response.setContentType("multipart/form-data");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
		ServletOutputStream out = response.getOutputStream();
		ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);

		Sheet sheet1 = new Sheet(1, 0);
		sheet1.setSheetName("第一个sheet");
		writer.write0(getStrings(), sheet1);
		writer.finish();

		out.flush();
		out.close();
	}

	@RequestMapping(value = "writeExcel", method = RequestMethod.GET)
	public void writeExcel(HttpServletResponse response) throws IOException {
		List<ExportInfo> list = getList();
		String fileName = "一个 Excel 文件";
		String sheetName = "第一个 sheet";

		ExcelUtil.writeExcel(response, list, fileName, sheetName, new ExportInfo());
	}
	
	@ResponseBody
	@RequestMapping(value = "/sendMail", produces = "application/json; charset=UTF-8", method = { RequestMethod.POST})
	public String sendMail(@RequestBody MailModel model) {
		Gson gson = new Gson();
		String reveiecer = model.getReceiever();
		String[] split = reveiecer.split(";", -1);
		for(String email:split) {
			if(!email.matches("\\w+(\\.\\w)*@\\w+(\\.\\w{2,3}){1,3}")) {
				return gson.toJson(new SingleResponse("-1"));
			}
		}
		try {
			MailUtils.sendMail(model.getReceiever(), model.getTitle(), model.getContent());
			return new Gson().toJson(new SingleResponse("0"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return gson.toJson(new SingleResponse("-1"));
		}
	}

	private static List<List<String>> getStrings() {
		List<List<String>> data = new ArrayList<List<String>>();
		for (int i = 0; i < 100; i++) {
			List<String> line = Arrays.asList("杭州", "中国", "浙江", "900万");
			data.add(line);
		}
		return data;
	}

	private List<ExportInfo> getList() {
		List<ExportInfo> list = new ArrayList<ExportInfo>();
		ExportInfo model1 = new ExportInfo();
		model1.setName("howie");
		model1.setAge("19");
		model1.setAddress("123456789");
		model1.setEmail("123456789@gmail.com");
		list.add(model1);
		ExportInfo model2 = new ExportInfo();
		model2.setName("harry");
		model2.setAge("20");
		model2.setAddress("198752233");
		model2.setEmail("198752233@gmail.com");
		list.add(model2);
		return list;
	}
}