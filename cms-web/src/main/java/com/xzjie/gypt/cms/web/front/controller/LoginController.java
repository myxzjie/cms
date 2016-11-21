package com.xzjie.gypt.cms.web.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("${web.frontPath}LoginController")
public class LoginController {

	@RequestMapping(value = "${web.frontPath}/login")
	public String login(){
		return "front/login";
	}
}
