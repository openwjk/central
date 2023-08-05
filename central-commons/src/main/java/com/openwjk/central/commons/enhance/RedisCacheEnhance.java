package com.openwjk.central.commons.enhance;

import com.openwjk.central.commons.domain.CacheableResultDTO;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.lang.Nullable;

import java.time.Duration;
import java.util.Objects;


/**
 * @author wangjunkai
 * @description
 * @date 2023/8/1 13:57
 */
@Log4j2
public class RedisCacheEnhance extends RedisCache {
    protected RedisCacheEnhance(String name, RedisCacheWriter cacheWriter, RedisCacheConfiguration cacheConfig) {
        super(name, cacheWriter, cacheConfig);
    }

    @Override
    public void put(Object key, @Nullable Object value) {
        if (value instanceof CacheableResultDTO) {
            CacheableResultDTO resultDTO = ((CacheableResultDTO) value);
            if (resultDTO.getEnterCache()) {
                Object cacheValue = preProcessCacheValue(value);
                if (!isAllowNullValues() && cacheValue == null) {
                    throw new IllegalArgumentException(String.format("Cache '%s' does not allow 'null' values. Avoid storing null via '@Cacheable(unless=\"#result == null\")' or configure RedisCache to allow 'null' via RedisCacheConfiguration.", getName()));
                }
                getNativeCache().put(getName(), createAndConvertCacheKey(key), serializeCacheValue(cacheValue), Duration.ofSeconds(resultDTO.getExpire()));
            } else {
                // don't cache
                log.info("call RedisCacheEnhance.put, value.enterCache = false, key:[{}], value:[{}]", key.toString(), value.toString());
            }
        } else {
            super.put(key, value);
        }
    }

    private byte[] createAndConvertCacheKey(Object key) {
        return serializeCacheKey(createCacheKey(key));
    }

    @Override
    public ValueWrapper get(Object key) {
        ValueWrapper wrapper = super.get(key);
        if (wrapper == null) {
            return null;
        }
        Object value = wrapper.get();
        if (value instanceof CacheableResultDTO) {
            ((CacheableResultDTO) value).setFromCache(Boolean.TRUE);
        } else {
            // nothing to do
        }
        return wrapper;
    }

}
