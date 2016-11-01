package com.xzjie.gypt.system.web.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class DefaultController extends BaseController{

	@RequestMapping(value="index")
	public String index(Map<String, Object> modelView){
		/*Account account=this.getCurrentAccount();
		if(StringUtils.isBlank(account.getNickName())){
			account.setNickName(account.getName());
		}
		modelView.put("account", account);
		modelView.put("org", this.getCurrentOrg());*/
		return "index";
	}
	
	@RequestMapping(value="home")
	public String home(){
		return "home";
	}
}
