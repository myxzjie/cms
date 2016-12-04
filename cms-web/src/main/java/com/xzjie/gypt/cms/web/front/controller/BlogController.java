package com.xzjie.gypt.cms.web.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "${web.frontPath}/blog")
public class BlogController {

	@RequestMapping(value = "myblog")
	public String myblog(){
		return "front/myblog";
	}
}
