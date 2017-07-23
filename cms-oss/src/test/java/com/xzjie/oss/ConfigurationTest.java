package com.xzjie.oss;

import org.junit.Test;

import com.xzjie.oss.conf.Configuration;
import com.xzjie.oss.conf.Configuration.OSS;

public class ConfigurationTest {

	@Test
	public void test(){
		Configuration conf=new Configuration();
		System.out.println(conf.getValue(OSS.endpoint));
	}
}
