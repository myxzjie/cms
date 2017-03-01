package com.xzjie.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
public class SystemCacheManager {
	
	private Cache cache;

	@Autowired
	public SystemCacheManager(CacheManager cacheManager) {
		this.cache = cacheManager.getCache("system-cache");
	}
	
	public Object get(String key){
		return cache.get(key).get();
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get(String key, Class<T> cls) {
		return (T) cache.get(key).get();
	}
	
	public void put(String key, Object value){
		cache.put(key, value);
    }
	
	public void evict(String key){
		cache.evict(key);
    }
	
	public void clear(){
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
