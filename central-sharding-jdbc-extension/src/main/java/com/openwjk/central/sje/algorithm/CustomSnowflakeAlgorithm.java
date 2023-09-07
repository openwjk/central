package com.openwjk.central.sje.algorithm;

import com.openwjk.central.commons.snowflake.SnowflakeService;
import org.apache.shardingsphere.sharding.spi.KeyGenerateAlgorithm;

/**
 * @author wangjunkai
 * @description
 * @date 2023/9/7 9:29
 */
public class CustomSnowflakeAlgorithm implements KeyGenerateAlgorithm {

    @Override
    public String getType() {
        return "CUSTOM_SNOWFLAKE";
    }


    @Override
    public Comparable<Long> generateKey() {
        return SnowflakeService.getInstance().getId();
    }
}
