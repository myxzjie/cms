package com.xzjie.cms.configure;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.util.Assert;
import org.springframework.web.server.session.CookieWebSessionIdResolver;
import org.springframework.web.server.session.WebSessionIdResolver;

import java.nio.charset.Charset;
import java.time.Duration;

@Configuration
@EnableCaching
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60*60*2)
public class RedisConfigure extends CachingConfigurerSupport {

    /**
     * 选择redis作为默认缓存工具
     *
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        // 生成一个默认配置，通过config对象即可对缓存进行自定义配置
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig()
                // 设置缓存的默认过期时间，也是使用Duration设置
                .entryTtl(Duration.ofDays(7))
                // 设置 key为string序列化
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(stringSerializer()))
                // 设置value为json序列化
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer()))
                // 不缓存空值
                .disableCachingNullValues();
        return RedisCacheManager
                .builder(redisConnectionFactory)
                .transactionAware()
                .cacheDefaults(configuration)
                .build();
    }

    @Bean
    public WebSessionIdResolver webSessionIdResolver() {
        CookieWebSessionIdResolver resolver = new CookieWebSessionIdResolver();
        // 重写定义 cookie 名字
        resolver.setCookieName("able-sid");
        return resolver;
    }

//    @Bean
//    public static ConfigureRedisAction configureRedisAction() {
//        return ConfigureRedisAction.NO_OP;
//    }
//
//    @Bean
//    public HeaderHttpSessionIdResolver httpSessionStrategy() {
//        return new HeaderHttpSessionIdResolver("x-auth-token");
//    }


    /**
     * retemplate相关配置
     *
     * @param factory
     * @return
     */
    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory factory) {

        StringRedisTemplate template = new StringRedisTemplate();
        // 配置连接工厂
        template.setConnectionFactory(factory);

        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(stringSerializer());
        // 值采用json序列化
        template.setValueSerializer(jackson2JsonRedisSerializer());
        // 设置hash key 和value序列化模式
        template.setHashKeySerializer(stringSerializer());
        template.setHashValueSerializer(jackson2JsonRedisSerializer());

        template.afterPropertiesSet();

        return template;
    }

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory factory) {

        RedisTemplate template = new RedisTemplate();
        // 配置连接工厂
        template.setConnectionFactory(factory);

        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(stringSerializer());
        // 值采用json序列化
        template.setValueSerializer(jackson2JsonRedisSerializer());
        // 设置hash key 和value序列化模式
        template.setHashKeySerializer(stringSerializer());
        template.setHashValueSerializer(jackson2JsonRedisSerializer());

        template.afterPropertiesSet();

        return template;
    }

    @Bean
    public RedisSerializer stringSerializer() {
        return new DefaultStringSerializer();
    }


    /**
     * json序列化
     *
     * @return
     */
    @Bean
    public RedisSerializer<Object> jackson2JsonRedisSerializer() {
        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);

        ObjectMapper objectMapper = new ObjectMapper();
        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        objectMapper.setVisibility(PropertyAccessor.ALL, Visibility.ANY);
        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
        //objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        objectMapper.activateDefaultTyping(objectMapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL, As.WRAPPER_ARRAY);

        serializer.setObjectMapper(objectMapper);
        return serializer;
    }

    public class DefaultStringSerializer implements RedisSerializer<Object> {

        private final Charset charset;

        public DefaultStringSerializer() {
            this(Charset.forName("UTF8"));
        }

        public DefaultStringSerializer(Charset charset) {
            Assert.notNull(charset, "Charset must not be null!");
            this.charset = charset;
        }

        @Override
        public byte[] serialize(Object o) throws SerializationException {
            return o == null ? null : String.valueOf(o).getBytes(charset);
        }

        @Override
        public Object deserialize(byte[] bytes) throws SerializationException {
            return bytes == null ? null : new String(bytes, charset);

        }
    }
}
