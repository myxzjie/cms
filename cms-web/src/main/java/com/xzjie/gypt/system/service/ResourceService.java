/**
 * radp-cms
 * @Title: ResourceService.java 
 * @Package com.xzjie.gypt.system.service
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年7月2日
 */
package com.xzjie.gypt.system.service;

import java.util.List;

import com.xzjie.gypt.common.service.BaseService;
import com.xzjie.gypt.system.model.Resource;

/**
 * @className ResourceService.java
 * @description TODO(添加描述) 
 * @author xzjie
 * @create 2016年7月2日 下午12:18:12 
 * @version V0.0.1 
 */
public interface ResourceService extends BaseService<Resource, Long>{

	List<Resource> getResourceTree(Long resourceId);
	
	/**
	 * tree
	 * @param userId
	 * @return
	 */
	List<Resource> getResourceUser(Long userId);
	
	/**
	 * item
	 * @param userId
	 * @return
	 */
	List<Resource> getResourceUserByUserId(Long userId);
}
