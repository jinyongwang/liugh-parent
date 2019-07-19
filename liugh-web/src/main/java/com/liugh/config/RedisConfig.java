package com.liugh.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author liugh
 * @since on 2018/5/11.
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    /*定义缓存数据 key 生成策略的bean
   包名+类名+方法名+所有参数
   */
    @Bean("wiselyKeyGenerator")
    @Override
    public KeyGenerator keyGenerator(){
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getSimpleName()+":");
                sb.append(method.getName()+":");
                for (Object obj : params) {
                    sb.append(obj.toString()+":");
                }
                return sb.deleteCharAt(sb.length()-1).toString();
            }
        };

    }
    @Bean
    public RedisTemplate redisTemplate() {

        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        //全局开关，支持jackson在反序列是使用多态
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        StringRedisTemplate redisTemplate = new StringRedisTemplate();
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;

    }

    /**载入配置文件配置的连接工厂**/
    @Autowired
    RedisConnectionFactory redisConnectionFactory;


    @Bean
    public CacheManager cacheManager() {

        RedisCacheManager cacheManager;

        RedisCacheConfiguration cacheConfiguration =RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofDays(1))//缓存1天
                .disableCachingNullValues();
        // 设置一个初始化的缓存空间set集合
        Set<String> cacheNames =  new HashSet<>();
        cacheNames.add("login");

        // 对每个缓存空间应用不同的配置
        Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
        configMap.put("login", cacheConfiguration.entryTtl(Duration.ofSeconds(30)));
        cacheManager = RedisCacheManager.builder(redisConnectionFactory)     // 使用自定义的缓存配置初始化一个cacheManager
                .initialCacheNames(cacheNames)  // 注意这两句的调用顺序，一定要先调用该方法设置初始化的缓存名，再初始化相关的配置
                .withInitialCacheConfigurations(configMap)
                .build();

        return cacheManager;


    }

}
