package com.openwjk.cds.registry;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.DisableInject;
import org.apache.dubbo.registry.Registry;
import org.apache.dubbo.registry.zookeeper.ZookeeperRegistryFactory;
import org.apache.dubbo.remoting.zookeeper.ZookeeperTransporter;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/27 10:08
 */
public class SwimLaneZookeeperRegistryFactory extends ZookeeperRegistryFactory {
    private ZookeeperTransporter zookeeperTransporter;

    @DisableInject
    public void setZookeeperTransporter(ZookeeperTransporter zookeeperTransporter) {
        this.zookeeperTransporter = zookeeperTransporter;
    }
    @Override
    public Registry createRegistry(URL url) {
        return new SwimLaneZookeeperRegistry(url, zookeeperTransporter);
    }
}
