/**
 * radp-cms
 * @Title: AccountServiceTest.java 
 * @Package com.xzjie.gypt.system
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年6月18日
 */
package com.xzjie.gypt.system;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.xzjie.gypt.BaseTest;
import com.xzjie.gypt.common.utils.constants.ConstantsUtils;
import com.xzjie.gypt.system.model.Account;
import com.xzjie.gypt.system.service.AccountService;

/**
 * @className AccountServiceTest.java
 * @description TODO(添加描述) 
 * @author xzjie
 * @create 2016年6月18日 下午5:36:17 
 * @version V0.0.1 
 */
public class AccountServiceTest extends BaseTest{

	@Autowired
	private AccountService accountService;
	
	@Test
	public void save(){
		Account record=new Account();
		record.setName("test");
		record.setStype("0");
		record.setPassword(ConstantsUtils.DEFAULT_PASSWORD);
		accountService.save(record);
	}
	
	@Test
	public void getAccountLogin(){
		Account account= accountService.getAccountLogin("root", "1");
		logger.info(">> account info:"+JSON.toJSONString(account));
	}
}
