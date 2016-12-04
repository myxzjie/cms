package com.xzjie.gypt.cms.web.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "${web.frontPath}/personal")
public class PersonalController {

	@RequestMapping(value = "info")
	public String info(){
		return "front/personalinfo";
	}
}
