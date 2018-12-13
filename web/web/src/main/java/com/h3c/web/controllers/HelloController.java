package com.h3c.web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/hello")
public class HelloController {
	
	@ResponseBody
	@RequestMapping(value="/sayHello",method= {RequestMethod.GET})
	public String sayHello(HttpServletRequest request,HttpServletResponse response,Model model) {
		return "hello SpringMVC";
	}
}