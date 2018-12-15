package com.h3c.web.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.h3c.service.base.models.User;
import com.h3c.service.base.utils.GetSpringBeanUtils;
import com.h3c.service.interfaces.IAccountService;

@Controller
@RequestMapping(value="/hello")
public class HelloController {
	int a = 0;
	@ResponseBody
	@RequestMapping(value="/sayHello",method= {RequestMethod.GET})
	public String sayHello(HttpServletRequest request,HttpServletResponse response,Model model) {
		a++;
		return "hello SpringMVC:"+a;
	}
	
	@ResponseBody
	@RequestMapping(value="json",produces="application/json; charset=UTF-8",method= {RequestMethod.GET})
	public String getJson() {
		List<String> list = new ArrayList<String>();
		list.add("apple");
		list.add("orange");
		list.add("nut");
		
		return new Gson().toJson(list);
	}
	
	@ResponseBody
	@RequestMapping(value="addUser",produces="application/json; charset=UTF-8",method= {RequestMethod.GET})
	public String addUser(String userName,String password) {
		IAccountService service = (IAccountService) GetSpringBeanUtils.getBean("AccountService");
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		service.save(user);
		Map<String, String> map = new HashMap<String, String>();
		map.put("success", "true");
		return new Gson().toJson(map);
	}
}