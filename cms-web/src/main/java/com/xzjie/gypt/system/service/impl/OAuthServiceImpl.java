/**
 * radp-cms
 * @Title: OAuthServiceImpl.java 
 * @Package com.xzjie.gypt.system.service.impl
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年8月2日
 */
package com.xzjie.gypt.system.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.xzjie.gypt.common.utils.StringUtils;
import com.xzjie.gypt.system.model.Org;
import com.xzjie.gypt.system.model.Resource;
import com.xzjie.gypt.system.model.Role;
import com.xzjie.gypt.system.service.OAuthService;
import com.xzjie.gypt.system.service.OrgService;
import com.xzjie.gypt.system.service.ResourceService;
import com.xzjie.gypt.system.service.RoleService;

/**
 * @className OAuthServiceImpl.java
 * @description TODO(添加描述) 
 * @author xzjie
 * @create 2016年8月2日 下午11:45:57 
 * @version V0.0.1 
 */
@Service("oAuthService")
public class OAuthServiceImpl implements OAuthService{
	
	private Cache cache;
	
	@Autowired
    private OrgService orgService;
	//private ClientService clientService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private ResourceService resourceService;
	
	@Autowired
    public OAuthServiceImpl(CacheManager cacheManager) {
        this.cache = cacheManager.getCache("code-cache");
    }

	@Override
	public void addAuthCode(String authCode, String username) {
		cache.put(authCode, username);		
	}

	@Override
	public void addAccessToken(String accessToken, String username) {
		cache.put(accessToken, username);
		
	}

	@Override
	public boolean checkAuthCode(String authCode) {
		return cache.get(authCode) != null;
	}

	@Override
	public boolean checkAccessToken(String accessToken) {
		return cache.get(accessToken) != null;
	}

	@Override
	public String getUsernameByAuthCode(String authCode) {
		 return (String)cache.get(authCode).get();
	}

	@Override
	public String getUsernameByAccessToken(String accessToken) {
		return (String)cache.get(accessToken).get();

	}

	@Override
	public long getExpireIn() {
		return 3600L;
	}

	@Override
	public boolean checkClientId(String clientId) {
		return orgService.checkOrgId(Long.valueOf(clientId));
	}

	@Override
	public boolean checkClientSecret(String clientSecret) {
		return orgService.checkClientSecret(clientSecret);
	}

	@Override
	public Set<String> getUserRoles(Long orgId, Long userId) {
		Set<String>  set=new HashSet<String>();
		List<Role> list=roleService.getUserRoles(orgId, userId);
		
		if(list==null){
			return null;
		}
		
		for (Role role : list) {
			set.add(role.getRoleCode());
		}
		
		return set;
	}

	@Override
	public Set<String> getPermissions(Long orgId, Long userId) {
		Set<String>  set=new HashSet<String>();
		List<Resource> list= resourceService.getResourceUserByUserId(userId);
		if(list==null){
			return null;
		}
		for (Resource resource : list) {
			if (StringUtils.isNotBlank(resource.getPermission())){
				// 添加基于Permission的权限信息
				for (String permission : StringUtils.split(resource.getPermission(),",")){
					set.add(permission);
				}
			}
		}
		return set;
	}

}
