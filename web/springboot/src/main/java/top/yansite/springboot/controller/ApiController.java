package top.yansite.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import top.yansite.springboot.model.UserInfo;
import top.yansite.springboot.services.interfaces.IUserInfoService;

@Controller
@RequestMapping("/api")
public class ApiController {

	@Autowired
	IUserInfoService service;
	
	@ResponseBody
	@RequestMapping(value="/getByName", method = RequestMethod.GET)
	public String getByName() {
		UserInfo info = service.getByName("kolgan");
		return new Gson().toJson(info);
	}
}
