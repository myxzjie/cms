/**
 * radp-cms
 * @Title: RoleService.java 
 * @Package com.xzjie.gypt.system.service
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年7月21日
 */
package com.xzjie.gypt.system.service;

import java.util.List;
import java.util.Map;

import com.xzjie.gypt.common.service.BaseService;
import com.xzjie.gypt.system.model.Role;

/**
 * @className RoleService.java
 * @description TODO(添加描述) 
 * @author xzjie
 * @create 2016年7月21日 下午10:13:23 
 * @version V0.0.1 
 */
public interface RoleService extends BaseService<Role, Long>{

	List<Role> getUserRoles(Long orgId,Long userId);
}
