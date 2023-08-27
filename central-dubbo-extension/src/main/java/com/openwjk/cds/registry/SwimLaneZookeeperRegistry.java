package com.openwjk.cds.registry;

import com.openwjk.cds.utils.SwimLaneUtil;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.registry.zookeeper.ZookeeperRegistry;
import org.apache.dubbo.remoting.zookeeper.ZookeeperTransporter;


/**
 * @author wangjunkai
 * @description
 * @date 2023/8/27 10:27
 */
public class SwimLaneZookeeperRegistry extends ZookeeperRegistry {

    public SwimLaneZookeeperRegistry(URL url, ZookeeperTransporter zookeeperTransporter) {
        super(url, zookeeperTransporter);
    }

    @Override
    public void doRegister(URL url) {
        String swinLaneNo = SwimLaneUtil.getSwimLaneNo();
        if (StringUtils.isNotBlank(swinLaneNo)) {
            url.addParameter("SWIM_LANE_NO", swinLaneNo);
        }
        super.doRegister(url);
    }

}
