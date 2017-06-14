package com.xzjie.gypt.system;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.xzjie.gypt.BaseTest;
import com.xzjie.gypt.system.model.Account;
import com.xzjie.gypt.system.service.AccountService;
import com.xzjie.gypt.system.service.impl.TestHelper;

public class TestServiceTest extends BaseTest{

	/*@Autowired
	private TestHelper testService;*/
	@Autowired
	private AccountService accountService;
	
	/*@Test
	public void test(){
		testService.get(1L);
	}*/
	@Test
	public void getAccountLogin(){
		Account account= accountService.getAccountLogin("root", "1");
		logger.info(">> account info:"+JSON.toJSONString(account));
	}
}
