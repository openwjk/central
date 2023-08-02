package com.openwjk.central.commons.enhance;

import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/2 9:17
 */
public class RedisCacheManagerEnhance extends RedisCacheManager {
    public RedisCacheManagerEnhance(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
        super(cacheWriter, defaultCacheConfiguration);
    }
}
