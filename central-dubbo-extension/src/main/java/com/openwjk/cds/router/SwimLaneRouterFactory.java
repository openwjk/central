package com.openwjk.cds.router;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.cluster.router.state.CacheableStateRouterFactory;
import org.apache.dubbo.rpc.cluster.router.state.StateRouter;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/29 11:26
 */
public class SwimLaneRouterFactory extends CacheableStateRouterFactory {



    @Override
    protected <T> StateRouter<T> createRouter(Class<T> interfaceClass, URL url) {
        return null;
    }
}
