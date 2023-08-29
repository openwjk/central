package com.openwjk.central.de.metadata;

import com.alibaba.fastjson2.JSONObject;
import com.openwjk.central.de.utils.SwimLaneUtil;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.metadata.report.identifier.MetadataIdentifier;
import org.apache.dubbo.metadata.store.zookeeper.ZookeeperMetadataReport;
import org.apache.dubbo.remoting.zookeeper.ZookeeperTransporter;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/28 16:23
 */
public class SwimLaneMetadataReport extends ZookeeperMetadataReport {

    public SwimLaneMetadataReport(URL url, ZookeeperTransporter zookeeperTransporter) {
        super(url, zookeeperTransporter);
    }

    @Override
    protected void doStoreProviderMetadata(MetadataIdentifier providerMetadataIdentifier, String serviceDefinitions) {
        String swinLaneNo = SwimLaneUtil.getSwimLaneNo();
        if (StringUtils.isNotBlank(serviceDefinitions) && StringUtils.isNotBlank(swinLaneNo)) {
            try {

                JSONObject object = JSONObject.parseObject(serviceDefinitions);
                if (object.containsKey("parameters")) {
                    JSONObject paramObj = (JSONObject) object.get("parameters");
                    paramObj.put("SWIM_LANE_NO", swinLaneNo);
                } else {
                    JSONObject paramObj = new JSONObject();
                    paramObj.put("SWIM_LANE_NO", swinLaneNo);
                    object.put("parameters", paramObj);
                }
                serviceDefinitions = JSONObject.toJSONString(object);
            } catch (Exception e) {
                //do somethine
            }
        }
        super.doStoreProviderMetadata(providerMetadataIdentifier, serviceDefinitions);
    }

}
