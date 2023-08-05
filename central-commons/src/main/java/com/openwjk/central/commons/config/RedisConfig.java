package com.openwjk.central.commons.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openwjk.central.commons.enhance.RedisCacheManagerEnhance;
import com.openwjk.central.commons.serialize.GenericFastJsonRedisSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.time.Duration;

/**
 * @author wangjunkai
 * @description
 * @date 2023/7/30 20:25
 */
@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    @Autowired
    LettuceConnectionFactory lettuceConnectionFactory;

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(lettuceConnectionFactory);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer(mapper);

        template.setValueSerializer(serializer);
        // 使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(RedisSerializer.string());
        template.afterPropertiesSet();
        return template;
    }


    @Bean("cacheManager")
    @Override
    public CacheManager cacheManager() {
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
        configuration.disableKeyPrefix();
        configuration.disableCachingNullValues();
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer(mapper);
        RedisSerializationContext.SerializationPair<Object> objectSerializationPair = RedisSerializationContext.SerializationPair.fromSerializer(serializer);
        configuration.serializeValuesWith(objectSerializationPair);
        RedisCacheManagerEnhance managerEnhance = new RedisCacheManagerEnhance(RedisCacheWriter.nonLockingRedisCacheWriter(lettuceConnectionFactory),
                configuration
        );
        return managerEnhance;
    }


}
