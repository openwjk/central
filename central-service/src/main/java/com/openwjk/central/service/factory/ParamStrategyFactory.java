package com.openwjk.central.service.factory;

import com.openwjk.central.service.service.IParamStrategy;
import com.openwjk.commons.exception.CommonsException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangjunkai
 * @description
 * @date 2023/12/21 15:33
 */
@Component
public class ParamStrategyFactory {
    private static final Logger logger = LoggerFactory.getLogger(ParamStrategyFactory.class);
    private static final Map<String, IParamStrategy> STRATEGY_MAP = new HashMap<>();
    @Autowired(required = false)
    private List<IParamStrategy> strategies;

    @PostConstruct
    public void init() {
        if (CollectionUtils.isEmpty(strategies)) {
            logger.warn("call ParamStrategyFactory.init, strategies is empty.");
            return ;
        }
        for (IParamStrategy strategy : strategies) {
            STRATEGY_MAP.put(strategy.primaryKey(), strategy);
        }
    }

    public IParamStrategy getStrategy(String primaryKey) {
        if (MapUtils.isEmpty(STRATEGY_MAP)) {
            throw new CommonsException("call ParamStrategyFactory.getStrategy, STRATEGY_MAP is empty......");
        }
        IParamStrategy strategy = STRATEGY_MAP.get(primaryKey);
        if (strategy == null) {
            throw new CommonsException(String.format("call ParamStrategyFactory.getStrategy, primaryKey:[%s], without this strategy.", primaryKey));
        }
        return strategy;
    }
}
