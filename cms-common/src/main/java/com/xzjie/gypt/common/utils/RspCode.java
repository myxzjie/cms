package com.xzjie.gypt.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Administrator
 *
 */
public class RspCode {

	/**
	 * code编号 00000
	 * code 开始两位数划分code的类型
	 * 10  权限和登录等
	 * 12 在业务逻辑上获得角色问题
	 * **/
	public static final String R00000 = "00000";
	//10 权限和登录等
	public static final String R10000 = "10000";
	public static final String R10001 = "10001";
	public static final String R10002 = "10002";
	public static final String R10003 = "10003";
	public static final String R10004 = "10004";
	public static final String R10005 = "10005";
	public static final String R10006 = "10006";
	public static final String R10007 = "10007";
	public static final String R10008 = "10008";
	public static final String R10009 = "10009";
	public static final String R10010 = "10010";
	public static final String R10011 = "10011";
	//用户code
	public static final String R11000 = "11000";
	public static final String R11001 = "11001";
	public static final String R11002 = "11002";
	//
	public static final String R12000 = "12000";
	public static final String R12001 = "12001";
	public static final String R12002 = "12002";
	/*public static final String R1002 = "1002";
	public static final String R1003 = "1003";
	public static final String R1004 = "1004";
	public static final String R1005 = "1005";
	public static final String R1006 = "1006";
	public static final String R1999 = "1999";*/
	//业务逻辑
	public static final String R20000 = "20000";
	public static final String R20001 = "20001";
	public static final String R20002 = "20002";
	public static final String R20003 = "20003";
	public static final String R20004 = "20004";
	public static final String R20005 = "20005";
	public static final String R20006 = "20006";
	//
	
	public static final String R30000 = "30000";
	public static final String R30001 = "30001";
	public static final String R30002 = "30002";
	public static final String R30003 = "30003";
	public static final String R30004 = "30004";
	//	
	/*public static final String R8000 = "8000";
	public static final String R8001 = "8001";
	public static final String R9001 = "9001";*/
	public static final String R99996 = "99996";
	public static final String R99997 = "99997";
	public static final String R99998 = "99998";
	public static final String R99999 = "99999";
    
	static Map<String, String> map = new HashMap<String, String>();
	static {
		map.put(R00000, "返回成功");
		map.put(R10000, "登录账号不存在");
		map.put(R10001, "登录用户名或密码错误");
		map.put(R10002, "登录用户类型不存在");
		map.put(R10003, "登录用户名为空");
		map.put(R10004, "登录密码为空");
		map.put(R10005, "登录app用户类型为空");
		map.put(R10006, "登录手机号被冻结");
		map.put(R10007, "登录token创建错误");
		map.put(R10008, "登录用户名不合法");
		map.put(R10009, "不能自动登录");
		map.put(R10010, "验证密码错误");
		//用户code
		map.put(R11000, "用户名已存在");
		map.put(R11001, "手机号已存在");
		map.put(R11002, "邮箱已存在");
		//
		map.put(R12000, "重复提交失败！");
		map.put(R12001, "修改失败,请重试...");
		map.put(R12002, "删除失败,请重试...");
		
		map.put(R20000, "添加角色和权限菜单失败");
		map.put(R20001, "协议请求失败");
		map.put(R20002, "接口错误");
		map.put(R20003, "分析接口报文错误");
		map.put(R20004, "接口返回的错误");
		map.put(R20005, "能力编号不符合");
		
		map.put(R30000, "客户端验证成功");
		map.put(R30001, "客户端验证失败，如错误的client_id/client_secret");
		map.put(R30002, "客户端client_id错误");
		map.put(R30003, "客户端安全client_secret错误");
		map.put(R30004, "错误的授权码");
		
		/*map.put("1001", "登录账号不存在");
		map.put("1002", "密码错误");
		map.put("1003", "无权限访问");
		//map.put("1004", "升级接口,找不到版本升级记录");
		map.put("1005", "短号呼叫群组接口错误");
		map.put("1999", "信息不存在");
		map.put("2000", "请求报文解析错误");
		map.put("2001", "请求报文格式有误,请检查请求报文是否合法的xml或json格式");
		map.put("2002", "请求报文参数有误");
		map.put("2003", "请求报文svccontent数据项格式校验失败");
		map.put("2030", "应答报文解析有误");
		map.put("8000", "服务器内部错误");
		map.put("8001", "服务器通信异常");
		map.put("9001", "手机端版本过低");*/
		map.put(R99996, "删除失败");
		map.put(R99997, "修改失败");
		map.put(R99998, "添加失败");
		map.put(R99999, "未知错误");
	}

	public static String getDesc(String bizCode) {
		return map.get(bizCode);
	}
	
	public static String getMsg(String bizCode) {
		String msg="错误编号:"+bizCode+",错误信息："+map.get(bizCode);
		return msg;
	}
	
	public static String getMsg(String bizCode,String info) {
		String msg="错误编号:"+bizCode+",错误信息："+info;
		return msg;
	}
	
	
}
