/**
 * radp-cms
 * @Title: RoleResourceService.java 
 * @Package com.xzjie.gypt.system.service
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年8月28日
 */
package com.xzjie.gypt.system.service;

import java.util.List;

import com.xzjie.gypt.common.service.BaseService;
import com.xzjie.gypt.system.model.RoleResource;

/**
 * @className RoleResourceService.java
 * @description TODO(添加描述) 
 * @author xzjie
 * @create 2016年8月28日 上午10:20:25 
 * @version V0.0.1 
 */
public interface RoleResourceService extends BaseService<RoleResource, Long>{

	void save(List<RoleResource> roleResources,Long roleId);
	
	List<RoleResource> getRoleResourceByRoleId(Long roleId);
}
