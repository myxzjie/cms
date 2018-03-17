package com.xzjie.common.web.utils;

import com.xzjie.core.msg.CodeMessageManager;
import com.xzjie.mybatis.page.Page;

import java.util.HashMap;
import java.util.Map;

public class MapResult {

	private static final String BIZ_CODE = "0";
	private static  final String ERROR_CODE="99999";

	public static Map<String, Object> mapOK() {

		Map<String, Object> modelMap = new HashMap<String, Object>(3);
		modelMap.put("code", BIZ_CODE);
		modelMap.put("message", CodeMessageManager.getMessage(BIZ_CODE));
		modelMap.put("success", true);

		return modelMap;
	}
	
	public static Map<String, Object> mapOK(String bizCode) {
		Map<String, Object> modelMap = new HashMap<String, Object>(3);
		modelMap.put("code", bizCode);
		modelMap.put("message", CodeMessageManager.getMessage(bizCode));
		modelMap.put("success", true);

		return modelMap;
	}

	public static Map<String, Object> mapOK(Object list) {
		Map<String, Object> modelMap = new HashMap<String, Object>(4);
		modelMap.put("data", list);
		modelMap.put("code", BIZ_CODE);
		modelMap.put("message", CodeMessageManager.getMessage(BIZ_CODE));
		modelMap.put("success", true);
		return modelMap;
	}

	public static Map<String, Object> mapOK(Object list, String bizCode) {
		Map<String, Object> modelMap = new HashMap<String, Object>(4);
		modelMap.put("data", list);
		modelMap.put("code", bizCode);
		modelMap.put("message", CodeMessageManager.getMessage(bizCode));
		modelMap.put("success", true);
		return modelMap;
	}

//	public static Map<String, Object> mapOK(Object list, String bizCode, String msg) {
//
//		Map<String, Object> modelMap = new HashMap<String, Object>(4);
//		modelMap.put("data", list);
//		modelMap.put("code", bizCode);
//		modelMap.put("message", (msg==null||msg=="")?CodeMessageManager.getMessage(bizCode):msg);
//		modelMap.put("success", true);
//		return modelMap;
//	}

	public static Map<String, Object> mapOK(Object list, Page page) {
		Map<String, Object> modelMap = new HashMap<String, Object>(5);
		modelMap.put("data", list);
		modelMap.put("page", page);
		modelMap.put("code", BIZ_CODE);
		modelMap.put("message", CodeMessageManager.getMessage(BIZ_CODE));
		modelMap.put("success", true);
		return modelMap;
	}

	public static Map<String, Object> dataTables(Object list, Page page) {
		Map<String, Object> modelMap = new HashMap<String, Object>(4);
		modelMap.put("data", list);
//		modelMap.put("code", BIZ_CODE);
//		modelMap.put("message", CodeMessageManager.getMessage(BIZ_CODE));
//		modelMap.put("success", true);
		modelMap.put("recordsTotal", page.getTotalResult());
		modelMap.put("recordsFiltered", page.getCurrentPage());
		modelMap.put("draw", page.getCurrentPage());
		return modelMap;
	}

	public static Map<String, Object> bootPage(Object list, Page page) {
		Map<String, Object> modelMap = new HashMap<String, Object>(2);
		modelMap.put("rows", list);
		modelMap.put("total", page.getTotalResult());
		modelMap.put("totalPage", page.getTotalPage());
		return modelMap;
	}

	
	public static Map<String, Object> mapOK(Object list, Page page, String bizCode) {

		Map<String, Object> modelMap = new HashMap<String, Object>(5);
		modelMap.put("data", list);
		modelMap.put("page", page);
		modelMap.put("code", bizCode);
		modelMap.put("message", CodeMessageManager.getMessage(bizCode));
		modelMap.put("success", true);

		return modelMap;
	}



	public static Map<String, Object> mapError() {

		Map<String, Object> modelMap = new HashMap<String, Object>(3);
		modelMap.put("code", ERROR_CODE);
		modelMap.put("message", CodeMessageManager.getMessage(ERROR_CODE));
		modelMap.put("success", false);

		return modelMap;
	}

	public static Map<String, Object> mapError(String bizCode) {

		Map<String, Object> modelMap = new HashMap<String, Object>(3);
		modelMap.put("code", bizCode);
		modelMap.put("message", CodeMessageManager.getMessage(bizCode));
		modelMap.put("success", false);

		return modelMap;
	}

	public static Map<String, Object> mapError(String bizCode, String msg) {

		Map<String, Object> modelMap = new HashMap<String, Object>(3);
		modelMap.put("code", bizCode);
		modelMap.put("message", msg);
		modelMap.put("success", false);

		return modelMap;
	}

}
