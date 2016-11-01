/**
 * radp-cms
 * @Title: RoleResourceEntity.java 
 * @Package com.xzjie.gypt.system.entity
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年8月28日
 */
package com.xzjie.gypt.system.entity;

import java.util.List;

import com.xzjie.gypt.system.model.RoleResource;

/**
 * @className RoleResourceEntity.java
 * @description TODO(添加描述)
 * @author xzjie
 * @create 2016年8月28日 上午9:38:30
 * @version V0.0.1
 */
public class RoleResourceEntity {

	private RoleResource model;
	private List<RoleResource> roleResources;

	public RoleResource getModel() {
		return model;
	}

	public void setModel(RoleResource model) {
		this.model = model;
	}

	public List<RoleResource> getRoleResources() {
		return roleResources;
	}

	public void setRoleResources(List<RoleResource> roleResources) {
		this.roleResources = roleResources;
	}

}
