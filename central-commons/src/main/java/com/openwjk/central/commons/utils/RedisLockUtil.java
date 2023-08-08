package com.openwjk.central.commons.utils;

import com.openwjk.commons.exception.CommonsException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/8 16:24
 */
@Component
public class RedisLockUtil {
    private static final Logger logger = LoggerFactory.getLogger(RedisLockUtil.class);
    private static final Logger desLogger = LoggerFactory.getLogger("DesLogger");
    private static final String UNIQUE_LOCK = "UNIQUE_LOCK";
    private static final String BLOCK_KEY = "BLOCK_KEY";
    @Autowired
    private RedisUtil redisUtil;

    public boolean tryLock(String lockKey, String uniqueKey, long expireSeconds) {
        desLogger.info("start to tryLock, key:[{}], value:[{}]", lockKey, uniqueKey);
        long startTimes = System.currentTimeMillis();
        try {
            if (StringUtils.isAnyBlank(lockKey, uniqueKey)) {
                desLogger.info("lockKey:[{}] value:[{}] may be is null", lockKey, uniqueKey);
                return Boolean.FALSE;
            }
            lockKey = CacheableUtil.buildCacheKey(UNIQUE_LOCK, lockKey);
            Boolean status = redisUtil.setIfAbsentAndExpire(lockKey, uniqueKey, expireSeconds);
            desLogger.info("tryLock:[{}] value:[{}] status:[{}]", lockKey, uniqueKey, status);
            return status != null ? status : Boolean.FALSE;
        } catch (Exception e) {
            desLogger.error(String.format("tryLock has error, key:[%s], value:[%s], msg:[%s]", lockKey, uniqueKey, e.getMessage()), e);
            releaseLock(lockKey, uniqueKey);
            return Boolean.FALSE;
        } finally {
            desLogger.info("tryLock costs:[{}], key:[{}], value:[{}]", System.currentTimeMillis() - startTimes, lockKey, uniqueKey);
        }
    }


    public boolean releaseLock(String lockKey, String uniqueKey) {
        desLogger.info("start to del lockKey:[{}], value:[{}]", lockKey, uniqueKey);
        long startTimes = System.currentTimeMillis();
        try {
            if (StringUtils.isAnyBlank(lockKey, uniqueKey)) {
                throw new CommonsException(String.format("lockKey:[%s] value:[%s] may be is null", lockKey, uniqueKey));
            }
            lockKey = CacheableUtil.buildCacheKey(UNIQUE_LOCK, lockKey);
            String value = redisUtil.get(lockKey);
            if (uniqueKey.equals(value)) {
                redisUtil.del(lockKey);
                desLogger.info("releaseLock:[{}] successfully", lockKey);
                return Boolean.TRUE;
            }
            desLogger.info("releaseLock:[{}] fail", lockKey);
            return Boolean.FALSE;
        } catch (Exception e) {
            desLogger.error(String.format("releaseLock has error when del lockKey:[%s], value:[%s] msg:%s",
                    lockKey, uniqueKey, e.getMessage()), e);
            return Boolean.FALSE;
        } finally {
            desLogger.info("releaseLock costs:[{}], lockKey:[{}], value:[{}]",
                    System.currentTimeMillis() - startTimes, lockKey, uniqueKey);
        }
    }
}
