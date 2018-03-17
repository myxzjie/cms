/**
 * radp-cms
 * @Title: TestBase.java 
 * @Package org.radp.xzjie.core
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年4月25日
 */
package com.xzjie.et;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @className TestBase
 * @description TODO(添加描述)
 * @author xiaozj
 * @create 2016年4月25日 下午2:01:02
 * @version V0.0.1 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml"/*,"classpath:spring-mybatis.xml"*/ })
public class BaseTest {

	/**
	 * Logger for this class
	 */
	protected static Logger logger = LoggerFactory.getLogger(BaseTest.class);
}