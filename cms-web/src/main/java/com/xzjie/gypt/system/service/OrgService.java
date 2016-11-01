/**
 * radp-cms
 * @Title: OrgService.java 
 * @Package com.xzjie.gypt.system.service
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年7月22日
 */
package com.xzjie.gypt.system.service;

import com.xzjie.gypt.common.service.BaseService;
import com.xzjie.gypt.system.model.Org;

/**
 * @className OrgService.java
 * @description TODO(添加描述) 
 * @author xzjie
 * @create 2016年7月22日 下午10:53:49 
 * @version V0.0.1 
 */
public interface OrgService extends BaseService<Org, Long>{

	boolean checkClientSecret(String clientSecret);
	
	boolean checkOrgId(Long orgId);
}
