/**
 * radp-cms
 * @Title: AccountController.java 
 * @Package com.xzjie.gypt.system.web.controller
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年7月18日
 */
package com.xzjie.gypt.system.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.ParameterStyle;
import org.apache.oltu.oauth2.common.utils.OAuthUtils;
import org.apache.oltu.oauth2.rs.request.OAuthAccessResourceRequest;
import org.apache.oltu.oauth2.rs.response.OAuthRSResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xzjie.gypt.common.page.Page;
import com.xzjie.gypt.common.page.PageEntity;
import com.xzjie.gypt.common.utils.RspCode;
import com.xzjie.gypt.common.utils.constants.ConstantsUtils;
import com.xzjie.gypt.common.utils.flexigrid.FlexigridJson;
import com.xzjie.gypt.common.utils.result.DataGridResult;
import com.xzjie.gypt.common.utils.result.MapResult;
import com.xzjie.gypt.system.entity.AccountEntity;
import com.xzjie.gypt.system.model.Account;
import com.xzjie.gypt.system.service.AccountService;
import com.xzjie.gypt.system.service.OAuthService;

/**
 * @className AccountController.java
 * @description TODO(添加描述) 
 * @author xzjie
 * @create 2016年7月18日 下午8:21:59 
 * @version V0.0.1 
 */
@Controller
@RequestMapping("account")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	@Autowired
    private OAuthService oAuthService;

	@RequiresPermissions("account:*")
	@RequestMapping("")
	public String index(){
		return "account/account_index";
	}
	
	@RequestMapping("edit")
	public String editView(Long id,Map<String, Object> modelMap){
		if(id!=null){
			AccountEntity record=accountService.getAccount(id);
			modelMap.put("model", record.getModel());
			modelMap.put("roleId", record.getRoleId());
		}
		return "account/account_edit";
	}
	
	@RequestMapping(value="save", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> save(Account model,AccountEntity parameter ){
		model.setStype("1");
		model.setPassword(ConstantsUtils.DEFAULT_PASSWORD);
		
		parameter.setModel(model);
		try{
			accountService.save(parameter);
			return MapResult.mapOK(RspCode.R00000);
		}catch(Exception e){
			return MapResult.mapError(RspCode.R99998,e.getMessage());
		}
		
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> update(Account model, AccountEntity parameter) {
		parameter.setModel(model);
		try {
			accountService.update(parameter);
			return MapResult.mapOK(RspCode.R00000);
		} catch (Exception e) {
			return MapResult.mapError(RspCode.R99998, e.getMessage());
		}

	}
	
	@RequestMapping(value="delete", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(Long id){
		if(accountService.delete(id)){
			return MapResult.mapOK(RspCode.R00000);
		}
		
		return MapResult.mapError(RspCode.R99997);
	}
	
	@RequestMapping(value="locked", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> locked(Long id){
		Account model=new Account();
		model.setUserId(id);
		model.setLocked(0);
		if(accountService.update(model)){
			return MapResult.mapOK(RspCode.R00000);
		}
		
		return MapResult.mapError(RspCode.R99997);
	}
	
	@RequestMapping(value="unlocked", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> unlocked(Long id){
		Account model=new Account();
		model.setUserId(id);
		model.setLocked(1);
		if(accountService.update(model)){
			return MapResult.mapOK(RspCode.R00000);
		}
		
		return MapResult.mapError(RspCode.R99997);
	}
	
	
	
	@RequestMapping(value="data")
	@ResponseBody
	public Map<String, Object> data(Account record,Page page){
		PageEntity<Account> pageEntity=new PageEntity<Account>();
		record.setStype("1");//人员类型 1操作人员
		pageEntity.setRecord(record);
		pageEntity.setPage(page);
		List<Account> resources= accountService.getListPage(pageEntity);
		return MapResult.mapOK(resources, pageEntity.getPage(), "OK");
		
	}
	
	@RequestMapping(value="flexigrid")
	@ResponseBody
	public String flexigrid(Account entity,Integer page, Integer rp){
		PageEntity<Account> pageEntity=new PageEntity<Account>();
		Page _page=new Page();
		_page.setCurrentPage(page);
		_page.setShowCount(rp);
		pageEntity.setRecord(entity);
		pageEntity.setPage(_page);
		List<Account> list=accountService.getListPage(pageEntity);
		DataGridResult<Account> gridResult=new DataGridResult<Account>();
		gridResult.setRows(list);
		gridResult.setTotal(_page.getTotalResult());
		
		return FlexigridJson.getJson(_page.getTotalPage(), list);
	}
	
	

    @RequestMapping("/info")
    public HttpEntity userInfo(HttpServletRequest request) throws OAuthSystemException {
        try {

            //构建OAuth资源请求
            OAuthAccessResourceRequest oauthRequest = new OAuthAccessResourceRequest(request, ParameterStyle.QUERY);
            //获取Access Token
            String accessToken = oauthRequest.getAccessToken();

            //验证Access Token
            if (!oAuthService.checkAccessToken(accessToken)) {
                // 如果不存在/过期了，返回未验证错误，需重新验证
                OAuthResponse oauthResponse = OAuthRSResponse
                        .errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
                        .setRealm(ConstantsUtils.RESOURCE_SERVER_NAME)//要写活的
                        .setError(OAuthError.ResourceResponse.INVALID_TOKEN)
                        .buildHeaderMessage();

                HttpHeaders headers = new HttpHeaders();
                headers.add(OAuth.HeaderType.WWW_AUTHENTICATE, oauthResponse.getHeader(OAuth.HeaderType.WWW_AUTHENTICATE));
                return new ResponseEntity(headers, HttpStatus.UNAUTHORIZED);
            }
            //返回用户名
            String username = oAuthService.getUsernameByAccessToken(accessToken);
            return new ResponseEntity(username, HttpStatus.OK);
        } catch (OAuthProblemException e) {
            //检查是否设置了错误码
            String errorCode = e.getError();
            if (OAuthUtils.isEmpty(errorCode)) {
                OAuthResponse oauthResponse = OAuthRSResponse
                        .errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
                        .setRealm(ConstantsUtils.RESOURCE_SERVER_NAME)//要写活的
                        .buildHeaderMessage();

                HttpHeaders headers = new HttpHeaders();
                headers.add(OAuth.HeaderType.WWW_AUTHENTICATE, oauthResponse.getHeader(OAuth.HeaderType.WWW_AUTHENTICATE));
                return new ResponseEntity(headers, HttpStatus.UNAUTHORIZED);
            }

            OAuthResponse oauthResponse = OAuthRSResponse
                    .errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
                    .setRealm(ConstantsUtils.RESOURCE_SERVER_NAME)//要写活的
                    .setError(e.getError())
                    .setErrorDescription(e.getDescription())
                    .setErrorUri(e.getUri())
                    .buildHeaderMessage();

            HttpHeaders headers = new HttpHeaders();
            headers.add(OAuth.HeaderType.WWW_AUTHENTICATE, oauthResponse.getHeader(OAuth.HeaderType.WWW_AUTHENTICATE));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
