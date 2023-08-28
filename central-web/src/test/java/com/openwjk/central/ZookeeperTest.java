package com.openwjk.central;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.jupiter.api.Test;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/28 18:56
 */
public class ZookeeperTest {
    @Test
    void name() throws Exception {
        //重试策略，初试时间1秒，重试10次
        RetryPolicy policy = new ExponentialBackoffRetry(1000, 10);

        //通过工厂创建Curator
        CuratorFramework client =
                CuratorFrameworkFactory.builder().connectString("zk.openwjk.com:2181")
                        .sessionTimeoutMs(60000).retryPolicy(policy).build();
        client.start();
        client.delete().guaranteed().deletingChildrenIfNeeded().forPath("/dubbo/metadata");
        client.delete().guaranteed().deletingChildrenIfNeeded().forPath("/dubbo/mapping");
        client.close();
    }
}
