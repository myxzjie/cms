package com.xzjie.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
public class SystemCacheManager {
	
	private static Cache cache;

	@Autowired
	public SystemCacheManager(CacheManager cacheManager) {
		SystemCacheManager.cache = cacheManager.getCache("system-cache");
	}
	
	public static Object get(String key){
		return cache.get(key).get();
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T get(String key, Class<T> cls) {
		return (T) cache.get(key).get();
	}
	
	public static void put(String key, Object value){
		cache.put(key, value);
    }
	
	public static void evict(String key){
		cache.evict(key);
    }
	
	public static void clear(){
		cache.clear();
    }
	
	
    
//    private static class SingletonContainer {
//		private static SystemCacheManager instance = new SystemCacheManager();
//	}
//
//	public static SystemCacheManager getInstance() {
//		return SingletonContainer.instance;
//	}

//	public Cache getCache() {
//		return cache;
//	}

/*	public void setCache(Cache cache) {
		this.cache = cache;
	}
*/
}
