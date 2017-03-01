package com.xzjie.cache;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xzjie.gypt.BaseTest;

public class TestSystemCacheManager extends BaseTest{

	@Autowired
	private SystemCacheManager cacheManager;
	
	@Test
	public void put(){
		cacheManager.put("test", "test-value");
	}
	
	@Test
	public void get(){
		System.out.println(cacheManager.get("test"));
	}
	
	@Test
	public void evict(){
		cacheManager.evict("test");
	}
	
	@Test
	public void clear(){
		cacheManager.clear();
	}
}
