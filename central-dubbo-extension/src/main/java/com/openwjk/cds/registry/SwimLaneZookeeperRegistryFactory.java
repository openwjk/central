package com.openwjk.cds.registry;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.DisableInject;
import org.apache.dubbo.registry.Registry;
import org.apache.dubbo.registry.zookeeper.ZookeeperRegistryFactory;
import org.apache.dubbo.remoting.zookeeper.ZookeeperTransporter;
import org.apache.dubbo.rpc.model.ApplicationModel;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/27 10:08
 */
public class SwimLaneZookeeperRegistryFactory extends ZookeeperRegistryFactory {
    private ZookeeperTransporter zookeeperTransporter;

    public SwimLaneZookeeperRegistryFactory() {
        this(ApplicationModel.defaultModel());
    }

    public SwimLaneZookeeperRegistryFactory(ApplicationModel applicationModel) {
        this.applicationModel = applicationModel;
        this.zookeeperTransporter = ZookeeperTransporter.getExtension(applicationModel);
    }

    @Override
    @DisableInject
    public void setZookeeperTransporter(ZookeeperTransporter zookeeperTransporter) {
        this.zookeeperTransporter = zookeeperTransporter;
    }

    @Override
    public Registry createRegistry(URL url) {
        return new SwimLaneZookeeperRegistry(url, zookeeperTransporter);
    }
}
