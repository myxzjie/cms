package com.xzjie.gypt.system.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xzjie.gypt.common.utils.RspCode;
import com.xzjie.gypt.common.utils.result.MapResult;
import com.xzjie.gypt.system.service.AccountService;


@Controller
public class DefaultController extends BaseController{

	@Autowired
	private AccountService accountService;
	
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
	
	@RequestMapping(value="changepwd",method=RequestMethod.GET)
	public String changePwd(){
		return "change_password";
	}
	
	@RequestMapping(value="changepwd")
	@ResponseBody
	public Map<String, Object> changePwd(String password,String newPassword,String confirmPassword, HttpServletRequest request){
		
		if(StringUtils.isBlank(password)){
			return MapResult.mapError(RspCode.R99999,"旧密码不能为空");
		}
		
		if(StringUtils.isBlank(newPassword)){
			return MapResult.mapError(RspCode.R99999,"新密码不能为空");
		}
		
		if(StringUtils.isBlank(confirmPassword)){
			return MapResult.mapError(RspCode.R99999,"确认密码不能为空");
		}
		
		if(!newPassword.equals(confirmPassword)){
			return MapResult.mapError(RspCode.R99999,"俩次密码不一致");
		}
		
		try {
			if (accountService.changePassword(getUserId(), password, newPassword)) {
//				SysLogsUtils.info("修改密码", "userId:"+getUserId()+",password:" + password + ",newpass:" + newPassword, "", "后台", 3, 3);

				return MapResult.mapOK(RspCode.R00000);
			}
		} catch (Exception e) {
//			SysLogsUtils.error("修改密码", "userId:"+getUserId()+",password:" + password + ",newpass:" + newPassword+",error msg:"+e.getMessage(), "后台", 2, 5);
			return MapResult.mapError(RspCode.R99999, e.getMessage());
		}
			
		
		return MapResult.mapError(RspCode.R99999,"密码修改失败");
	}
}
