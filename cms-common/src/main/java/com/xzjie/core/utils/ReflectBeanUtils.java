package com.xzjie.core.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 使用reflect进行转换
 * getDeclaredFields()会获取所有属性，需要过滤不需要的类型如 static final
 * 不会获取父类的属性，需要自己编码实现
 * 耗时与Introspector相比：1:100
 * 
 * Created by xzjie on 2017/5/16.
 */
public class ReflectBeanUtils {
	
	public static Object mapToObject(Map<String,Object> map,Class<?> clszz) throws Exception{
		if (map == null || map.size() <= 0)  return null;
		
		Object obj = clszz.newInstance();
		//获取关联的所有类，本类以及所有父类
        boolean ret = true;
		
		Class<?> cls = obj.getClass();
		List<Class<?>> clazzs = new ArrayList<Class<?>>();
		while (ret) {
            clazzs.add(cls);
            cls = cls.getSuperclass();
            if (cls == null || cls == Object.class) break;
        }
		for (int i = 0; i < clazzs.size(); i++) {
            Field[] fields = clazzs.get(i).getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                //由字符串转换回对象对应的类型
                if (field != null) {
                    field.setAccessible(true);
                    field.set(obj, map.get(field.getName()));
                }
            }
        }
        return obj;
	}
	
//    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws InstantiationException, IllegalAccessException  {
//        if (map == null || map.size() <= 0)
//            return null;
//
//        Object obj = beanClass.newInstance();
//        //获取关联的所有类，本类以及所有父类
//        boolean ret = true;
//        Class<?> cls = obj.getClass();
//        List<Class<?>> clazzs = new ArrayList<Class<?>>();
//        while (ret) {
//            clazzs.add(cls);
//            cls = cls.getSuperclass();
//            if (cls == null || cls == Object.class) break;
//        }
//
//        for (int i = 0; i < clazzs.size(); i++) {
//            Field[] fields = clazzs.get(i).getDeclaredFields();
//            for (Field field : fields) {
//                int mod = field.getModifiers();
//                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
//                    continue;
//                }
//                //由字符串转换回对象对应的类型
//                if (field != null) {
//                    field.setAccessible(true);
//                    field.set(obj, map.get(field.getName()));
//                }
//            }
//        }
//        return obj;
//    }

    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        if (obj == null) {
            return null;
        }
        //获取关联的所有类，本类以及所有父类
        boolean ret = true;
        Class<?> cls = obj.getClass();
        List<Class<?>> clazzs = new ArrayList<Class<?>>();
        while (ret) {
            clazzs.add(cls);
            cls = cls.getSuperclass();
            if (cls == null || cls == Object.class) break;
        }

        Map<String, Object> map = new HashMap<String, Object>();

        for (int i = 0; i < clazzs.size(); i++) {
            Field[] declaredFields = clazzs.get(i).getDeclaredFields();
            for (Field field : declaredFields) {
                int mod = field.getModifiers();
                //过滤 static 和 final 类型
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj));
            }
        }

        return map;
    }
}
