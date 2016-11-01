/**
 * ask_rear
 * @Title: ConstantsUtils.java 
 * @Package com.ask.rear.utils
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年2月19日
 */
package com.xzjie.gypt.common.utils.constants;

/**
 * @className ConstantsUtils
 * @description TODO(添加描述)
 * @author xiaozj
 * @create 2016年2月19日 下午12:45:52
 * @version V0.0.1 
 */
public abstract class ConstantsUtils {

	public static final String SESSION_USER = "SESSION_USER";
	public static final String SESSION_USER_ROEL = "SESSION_USER_ROLE";
	public static final String COOKIE_USER = "COOKIE_USER";
	public static final String SESSION_ORG = "SESSION_ORG";
	public static final String SESSION_WECHAT_ACCESS="SESSION_WECHAT_APPID";
	public static final String SESSION_WECHAT_ACCESS_TOKEN="SESSION_WECHAT_ACCESS_TOKEN";
	public static final String DEFAULT_PASSWORD="123qwe";
	public static final String RESOURCE_SERVER_NAME="CMS_ID";
	
	
	
	//@Value(value="${account.stype.operator}")
	public static final String ACCOUNT_STYPE_OPERATOR="1";
	public static final String ACCOUNT_STYPE_APP="2";
	
	//用户冻结状态 1:正常,0:冻结
	public static final String ACCOUNT_LOCKED="0";
	public static final String ACCOUNT_NOT_LOCKED="1";
	//用户登录的类型
	public static final String LOGIN_USERNAME_TYPE_NAME="1";
	public static final String LOGIN_USERNAME_TYPE_MOBILE="2";
	public static final String LOGIN_USERNAME_TYPE_EMAIL="3";
	
	/**
	 * 菜单类型 1:菜单节点，2功能节点
	 * 1:菜单节点
	 */
	public static final String MENU_TYPE_NODE="1";
	/**
	 * 菜单类型 1:菜单节点，2功能节点
	 * 2功能节点
	 */
	public static final String MENU_TYPE_CHILD="2";
	
	/**
	 * 教练员组
	 */
	public static final String DICT_COACH_GROUP="coach_group";
	
	/**
	 * 学员类型
	 */
	public static final String DICT_STU_TRAINEE_TYPE="stu_trainee_type";
	
	/**
	 * 学号
	 */
	public static final String DICT_STU_NO="stu_no";
	
	/**
	 * 流水号
	 */
	public static final String DICT_SERIAL_NUMBER="serial_number";
	
	/**
	 * 车类型
	 */
	public static final String DICT_CAR_TYPE="car_type";
	
	/**
	 * 学员状态
	 */
	public static final String DICT_STU_STATUS="stu_status";
}
