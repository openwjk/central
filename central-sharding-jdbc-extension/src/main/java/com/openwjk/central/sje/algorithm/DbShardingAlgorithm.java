package com.openwjk.central.sje.algorithm;

import org.apache.shardingsphere.infra.util.exception.ShardingSpherePreconditions;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;
import org.apache.shardingsphere.sharding.exception.data.NullShardingValueException;

import java.util.Collection;

/**
 * @author wangjunkai
 * @description
 * @date 2023/9/5 14:38
 */
public class DbShardingAlgorithm implements StandardShardingAlgorithm<Long> {

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
        ShardingSpherePreconditions.checkNotNull(shardingValue.getValue(), NullShardingValueException::new);
        return shardingValue.getValue() % 4 < 2 ? "ds0" : "ds1";
    }

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Long> shardingValue) {
        return null;
    }
}
