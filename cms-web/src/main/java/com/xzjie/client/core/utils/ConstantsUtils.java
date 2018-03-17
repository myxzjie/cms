package com.xzjie.client.core.utils;

public class ConstantsUtils extends com.xzjie.common.utils.ConstantsUtils{

	public final static long EXPIRE_TIME = 7*24*60*60;
	public final static String PASSWORD_RETRY_EXPIRETIME="CACHE_PASSWORD_RETRY_KEY";



	private final static String  REDIS_ACOUNT="acount_";

	public static  String getRedisAcountKey(String key){
		return REDIS_ACOUNT+key;
	}
}
