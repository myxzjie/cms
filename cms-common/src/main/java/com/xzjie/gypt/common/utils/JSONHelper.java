/**
 * radp-cms
 * @Title: JSONHelper.java 
 * @Package com.xzjie.gypt.common.utils
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年9月4日
 */
package com.xzjie.gypt.common.utils;

import java.io.IOException;

/**
 * @className JSONHelper.java
 * @description TODO(添加描述) 
 * @author xzjie
 * @create 2016年9月4日 下午11:27:37 
 * @version V0.0.1 
 */
public class JSONHelper {
	//Json字符串 转 实体对象  
    public static final <T> T getObject(String json,Class<T> clazz) throws IOException {  
        return com.alibaba.fastjson.JSONObject.parseObject(json, clazz);  
    }  
}
