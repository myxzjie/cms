package com.xzjie.gypt.common.utils.result;

import java.util.HashMap;
import java.util.Map;

import com.xzjie.gypt.common.page.Page;
import com.xzjie.gypt.common.utils.RspCode;



public class MapResult {
	
	public static Map<String, Object> mapOK(String bizCode) {

		Map<String, Object> modelMap = new HashMap<String, Object>(3);
		modelMap.put("code", bizCode);
		modelMap.put("message", RspCode.getDesc(bizCode));
		modelMap.put("success", true);

		return modelMap;
	}
	
	public static Map<String, Object> mapOK(Object list) {

		Map<String, Object> modelMap = new HashMap<String, Object>(4);
		modelMap.put("data", list);
		modelMap.put("code", RspCode.R00000);
		modelMap.put("message", RspCode.getDesc(RspCode.R00000));
		modelMap.put("success", true);
		return modelMap;

	}

	public static Map<String, Object> mapOK(Object list,String msg) {

		Map<String, Object> modelMap = new HashMap<String, Object>(4);
		modelMap.put("data", list);
		modelMap.put("code", RspCode.R00000);
		modelMap.put("message", msg);
		modelMap.put("success", true);
		return modelMap;

	}

	public static Map<String, Object> mapOK(Object list, Page page, String msg) {

		Map<String, Object> modelMap = new HashMap<String, Object>(4);
		modelMap.put("data", list);
		modelMap.put("page", page);
		modelMap.put("code", RspCode.R00000);
		modelMap.put("message", msg);
		modelMap.put("success", true);
		return modelMap;

	}

	public static Map<String, Object> mapError(String bizCode) {

		Map<String, Object> modelMap = new HashMap<String, Object>(3);
		modelMap.put("code", bizCode);
		modelMap.put("message", RspCode.getDesc(bizCode));
		modelMap.put("success", false);

		return modelMap;
	}
	
	public static Map<String, Object> mapError(String bizCode,String msg) {

		Map<String, Object> modelMap = new HashMap<String, Object>(3);
		modelMap.put("code", bizCode);
		modelMap.put("message", msg);
		modelMap.put("success", false);

		return modelMap;
	}
	

	/*
	 * public static Map<String,Object> mapOK(String dataName,Object list,
	 * String msg) {
	 * 
	 * Map<String,Object> modelMap = new HashMap<String,Object>(3);
	 * modelMap.put(dataName, list); modelMap.put("message", msg);
	 * modelMap.put("success", true); return modelMap;
	 * 
	 * }
	 * 
	 * public static Map<String,Object> mapOK(Object list,int count, String msg)
	 * {
	 * 
	 * Map<String,Object> modelMap = new HashMap<String,Object>(3);
	 * modelMap.put("data", list); modelMap.put("length",count);
	 * modelMap.put("message", msg); modelMap.put("success", true); return
	 * modelMap;
	 * 
	 * }
	 * 
	 * public static Map<String,Object> mapError(String msg,int status){
	 * 
	 * Map<String,Object> modelMap = new HashMap<String,Object>(3);
	 * modelMap.put("message", msg); modelMap.put("status",status);
	 * modelMap.put("success", false);
	 * 
	 * return modelMap; }
	 * 
	 * 
	 * 
	 * public static Map<String,Object> mapOK(String msg){
	 * 
	 * Map<String,Object> modelMap = new HashMap<String,Object>(2);
	 * modelMap.put("message", msg); modelMap.put("success", true);
	 * 
	 * return modelMap; }
	 * 
	 * public static Map<String,Object> mapOK(boolean isValid){
	 * 
	 * Map<String,Object> modelMap = new HashMap<String,Object>(2);
	 * modelMap.put("isValid", isValid); modelMap.put("success", true);
	 * 
	 * return modelMap; }
	 */
}
