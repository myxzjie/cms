package com.xzjie.et.core.utils;

public class ConstantsUtils extends com.xzjie.common.utils.ConstantsUtils {

	public final static long EXPIRE_TIME = 7*24*60*60;
	public final static long CODE_EXPIRE_TIME = 7200;
	public final static String PASSWORD_RETRY_EXPIRETIME="CACHE_PASSWORD_RETRY_KEY";
	public final static String ENABLED="1";

	private final static String  REDIS_ACOUNT="acount_";



	public static  String getRedisAcountKey(String key){
		return REDIS_ACOUNT+key;
	}

	public enum ACCOUNTLOCKED{
		YES(1,"正常"),
		NO(0,"冻结");

		private int index;
		private String name;

		private ACCOUNTLOCKED(int index,String name){
			this.index=index;
			this.name=name;
		}

		public int getIndex() {
			return index;
		}

		public String getName() {
			return name;
		}

	}
}
