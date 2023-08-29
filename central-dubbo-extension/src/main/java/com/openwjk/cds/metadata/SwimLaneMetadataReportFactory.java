package com.openwjk.cds.metadata;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.DisableInject;
import org.apache.dubbo.metadata.report.MetadataReport;
import org.apache.dubbo.metadata.store.zookeeper.ZookeeperMetadataReportFactory;
import org.apache.dubbo.remoting.zookeeper.ZookeeperTransporter;
import org.apache.dubbo.rpc.model.ApplicationModel;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/28 17:26
 */
public class SwimLaneMetadataReportFactory extends ZookeeperMetadataReportFactory {


    private ZookeeperTransporter zookeeperTransporter;

    private ApplicationModel applicationModel;

    public SwimLaneMetadataReportFactory(ApplicationModel applicationModel) {
        super(applicationModel);
        this.applicationModel = applicationModel;
        this.zookeeperTransporter = ZookeeperTransporter.getExtension(applicationModel);
    }

    @Override
    @DisableInject
    public void setZookeeperTransporter(ZookeeperTransporter zookeeperTransporter) {
        this.zookeeperTransporter = zookeeperTransporter;
    }

    @Override
    public MetadataReport createMetadataReport(URL url) {
        return new SwimLaneMetadataReport(url, zookeeperTransporter);
    }
}
