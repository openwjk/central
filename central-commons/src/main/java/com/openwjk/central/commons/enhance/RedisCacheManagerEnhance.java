package com.openwjk.central.commons.enhance;

import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.lang.Nullable;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/2 9:17
 */
public class RedisCacheManagerEnhance extends RedisCacheManager {
    private RedisCacheWriter cacheWriter;
    private RedisCacheConfiguration defaultCacheConfiguration;

    @Override
    protected RedisCache createRedisCache(String name, @Nullable RedisCacheConfiguration cacheConfig) {
        return new RedisCacheEnhance(name, cacheWriter, cacheConfig != null ? cacheConfig : defaultCacheConfiguration);
    }

    public RedisCacheManagerEnhance(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
        super(cacheWriter, defaultCacheConfiguration);
        this.cacheWriter = cacheWriter;
        this.defaultCacheConfiguration = defaultCacheConfiguration;
    }
}
