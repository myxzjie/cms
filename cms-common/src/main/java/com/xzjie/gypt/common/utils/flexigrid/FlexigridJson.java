/**
 * radp-cms
 * @Title: FlexigridJson.java 
 * @Package com.xzjie.gypt.common.utils.flexigrid
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年7月6日
 */
package com.xzjie.gypt.common.utils.flexigrid;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * @className FlexigridJson.java
 * @description TODO(添加描述)
 * @author xzjie
 * @create 2016年7月6日 下午10:14:14
 * @version V0.0.1
 */
public class FlexigridJson {
	
	private static final Logger logger = LoggerFactory.getLogger(FlexigridJson.class);

	public static <T> String getJson(int page, List<T> object) {
		String result = "";
		int total = object.size();
		List<FlexigridRows<T>> frlist = new ArrayList<FlexigridRows<T>>();
		for (int i = 0; i < total; i++) { // 使用for循环为rows中的每个id赋值.
			FlexigridRows<T> rows = new FlexigridRows<T>(i, object.get(i));
			frlist.add(rows);
		}
		FlexigridModel<T> model = new FlexigridModel<T>(total, page, frlist);
		
		result=JSON.toJSONString(model);
		
		if(logger.isDebugEnabled()){
			logger.debug("==>> flexigrid json:"+result);
		}
		
		//Gson gson = new Gson();
		//result = gson.toJson(fj); // 使用Gson生产json
		// System.out.println(result);
		return result;
	}
}
