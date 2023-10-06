package com.openwjk.central.sje.algorithm;

import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.util.Collection;

/**
 * @author wangjunkai
 * @description
 * @date 2023/9/5 14:38
 */
public class DefaultDbShardingAlgorithm implements StandardShardingAlgorithm {


    @Override
    public String doSharding(Collection availableTargetNames, PreciseShardingValue shardingValue) {
        return "ds";
    }

    @Override
    public Collection<String> doSharding(Collection availableTargetNames, RangeShardingValue shardingValue) {
        return null;
    }
}
