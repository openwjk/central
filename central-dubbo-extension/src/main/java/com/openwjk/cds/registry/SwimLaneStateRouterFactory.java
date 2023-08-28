package com.openwjk.cds.registry;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.DisableInject;
import org.apache.dubbo.registry.Registry;
import org.apache.dubbo.registry.zookeeper.ZookeeperRegistryFactory;
import org.apache.dubbo.remoting.zookeeper.ZookeeperTransporter;
import org.apache.dubbo.rpc.cluster.router.AbstractRouterRule;
import org.apache.dubbo.rpc.cluster.router.state.CacheableStateRouterFactory;
import org.apache.dubbo.rpc.cluster.router.state.StateRouter;
import org.apache.dubbo.rpc.cluster.router.state.StateRouterFactory;
import org.apache.dubbo.rpc.model.ApplicationModel;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/27 10:08
 */

public class SwimLaneStateRouterFactory extends CacheableStateRouterFactory {

    @Override
    protected <T> StateRouter<T> createRouter(Class<T> interfaceClass, URL url) {
        return new SwimLaneStateRouter(url);
    }
}
