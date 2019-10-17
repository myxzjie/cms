package com.xzjie.cms.core.utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;

@Slf4j
public class JsonUtils {
   private static ObjectMapper objectMapper = new Jackson2ObjectMapperBuilder().factory(new JsonFactory()).build();


//    static {
//        //将对象的所有字段全部列入
//        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.ALWAYS);
//
//        //取消默认转换timestamps形式,false使用日期格式转换，true不使用日期转换，结果是时间的数值157113793535
//        objectMapper.configure(SerializationConfig.Feature.WRITE_DATE_KEYS_AS_TIMESTAMPS,false); //默认值true
//
//        //忽略空Bean转json的错误
//        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS,false);
//
//        //所有的日期格式统一样式： yyyy-MM-dd HH:mm:ss
//        objectMapper.setDateFormat(new SimpleDateFormat(DateTimeUtil.STANDARD_FORMAT));
//
//        //忽略 在json字符串中存在，但是在对象中不存在对应属性的情况，防止错误。
//        // 例如json数据中多出字段，而对象中没有此字段。如果设置true，抛出异常，因为字段不对应；false则忽略多出的字段，默认值为null，将其他字段反序列化成功
//        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_NULL_FOR_PRIMITIVES,false);
//
//    }

    //将单个对象转换成json格式的字符串（没有格式化后的json）
    public static <T> String toJsonString(T obj){
        if (obj == null){
            return null;
        }
        try {
            return obj instanceof String ? (String) obj:objectMapper.writeValueAsString(obj);
        } catch (IOException e) {
            log.warn("Parse object to String error", e);
            return null;
        }
    }

    //将单个对象转换成json格式的字符串（格式化后的json）
    public static <T> String toJsonStringPretty(T obj){
        if (obj == null){
            return null;
        }
        try {
            return obj instanceof String ? (String) obj:objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (IOException e) {
            log.warn("Parse object to String error", e);
            return null;
        }
    }
    //将json形式的字符串数据转换成单个对象
    public static <T> T toObject(String str, Class<T> clazz){
        if (StringUtils.isEmpty(str) || clazz == null){
            return null;
        }
        try {
            return clazz.equals(String.class) ? (T) str : objectMapper.readValue(str,clazz);
        } catch (IOException e) {
            log.warn("Parse object to Object error", e);
            return null;
        }
    }

    //将json形式的字符串数据转换成多个对象
    public static <T> T toObject(String str, TypeReference<T> typeReference){
        if (StringUtils.isEmpty(str) || typeReference == null){
            return null;
        }
        try {
            return typeReference.getType().equals(String.class) ? (T) str : (T) objectMapper.readValue(str, typeReference);
        } catch (IOException e) {
            log.warn("Parse object to Object error", e);
            return null;
        }
    }

    //将json形式的字符串数据转换成多个对象
    public static <T> T toObject(String str, Class<T> collectionClass, Class<?>... elementClasses){
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass,elementClasses);
        try {
            return objectMapper.readValue(str, javaType);
        } catch (IOException e) {
            log.warn("Parse object to Object error", e);
            return null;
        }
    }
}
