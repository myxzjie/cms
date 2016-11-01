/**
 * radp-cms
 * @Title: ResourceServiceTest.java 
 * @Package com.xzjie.gypt.system
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年7月2日
 */
package com.xzjie.gypt.system;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.xzjie.gypt.BaseTest;
import com.xzjie.gypt.system.model.Resource;
import com.xzjie.gypt.system.service.ResourceService;

/**
 * @className ResourceServiceTest.java
 * @description TODO(添加描述) 
 * @author xzjie
 * @create 2016年7月2日 下午12:25:27 
 * @version V0.0.1 
 */
public class ResourceServiceTest extends BaseTest{
	
	@Autowired
	private ResourceService resourceService;

	@Test
	public void getResourceTree(){
		List<Resource> list= resourceService.getResourceTree(0L);
		
		logger.info("==> resource tree:"+JSON.toJSONString(list));
	}
	
	
	@Test
	public void getResourceUser(){
		List<Resource> list= resourceService.getResourceUser(1L);
		
		logger.info("==> resource user:"+JSON.toJSONString(list));
	}
}
