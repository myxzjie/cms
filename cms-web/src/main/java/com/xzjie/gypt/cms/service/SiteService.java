/**
 * radp-cms
 * @Title: SiteService.java 
 * @Package com.xzjie.gypt.cms.service
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年7月31日
 */
package com.xzjie.gypt.cms.service;

import com.xzjie.gypt.cms.model.Site;
import com.xzjie.gypt.common.service.BaseService;

/**
 * @className SiteService.java
 * @description TODO(添加描述) 
 * @author xzjie
 * @create 2016年7月31日 下午4:14:52 
 * @version V0.0.1 
 */
public interface SiteService extends BaseService<Site, Long>{

	
	Site getSiteByOrgId(Long orgId);
}
