package com.xzjie.gypt.cms.web.front.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xzjie.gypt.cms.model.Article;
import com.xzjie.gypt.cms.service.ArticleService;
import com.xzjie.gypt.cms.service.CategoryService;
import com.xzjie.gypt.common.page.Page;
import com.xzjie.gypt.common.page.PageEntity;
import com.xzjie.gypt.common.utils.DateUtils;
import com.xzjie.gypt.common.utils.RspCode;
import com.xzjie.gypt.common.utils.StringUtils;
import com.xzjie.gypt.common.utils.result.MapResult;
import com.xzjie.gypt.system.model.Account;
import com.xzjie.gypt.system.service.AccountService;
import com.xzjie.gypt.system.web.controller.BaseController;

@Controller
@RequestMapping(value = "${web.frontPath}/personal")
public class PersonalController extends BaseController{
	
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "info")
	public String info(Article model,Page page,Map<String, Object> modelMap){
		categoryService.setCategoryList(getSiteId(), modelMap);
		
		PageEntity<Article> record=new PageEntity<>();
		
		model.setSiteId(getSiteId());
		model.setAuthor(this.getUserId());
		
		record.setPage(page);
		record.setRecord(model);
		
		List<Article>  articles=articleService.getListPage(record);
		
		modelMap.put("articles", articles);
		modelMap.put("totalPage", page.getTotalPage());
		
		return "front/personalinfo";
	}
	
	@RequestMapping(value = "edit")
	public String edit(Map<String, Object> modelMap){
		
		Account account= accountService.get(this.getUserId());
		
		Integer age=null;
		
		if(account.getBirtn()!=null){
			age=DateUtils.getCurrentAgeByBirthDate(account.getBirtn());
		}
		
		modelMap.put("model", account);
		modelMap.put("age",age);
		
		return "front/personaledit";
	}
	
	@RequestMapping(value = "update")
	@ResponseBody
	public Map<String, Object> update(Account account,Map<String, Object> modelMap){
		
		if(account.getUserId()==null){
			return MapResult.mapError(RspCode.R99998, "修改失败，请刷再重试...");
		}
		
		if(StringUtils.isBlank(account.getName())){
			return MapResult.mapError(RspCode.R99998, "用户名称不能为空！");
		}
		
		if(StringUtils.isBlank(account.getNickName())){
			return MapResult.mapError(RspCode.R99998, "昵称不能为空！");
		}
		
		if(StringUtils.isBlank(account.geteMail())){
			return MapResult.mapError(RspCode.R99998, "邮箱称不能为空！");
		}
		
		if(StringUtils.isBlank(account.getPhone())){
			return MapResult.mapError(RspCode.R99998, "手机号不能为空！");
		}
		
		if(accountService.isNameExist(account.getName(), account.getUserId())){
			return MapResult.mapError(RspCode.R99998, "用户名已存在！");
		}
		
		if(accountService.isEmailExist(account.geteMail(), account.getUserId())){
			return MapResult.mapError(RspCode.R99998, "邮箱已存在！");
		}
		
		if(accountService.isPhoneExist(account.getPhone(), account.getUserId())){
			return MapResult.mapError(RspCode.R99998, "手机号已存在！");
		}
		
		if(accountService.update(account)){
			return MapResult.mapOK(RspCode.R00000);
		}else{
			return MapResult.mapError(RspCode.R99998, "修改失败！请重试...s");
		}
		
	}
}
