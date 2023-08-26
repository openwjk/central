package com.openwjk.cds.loadbalance;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.utils.CollectionUtils;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.cluster.LoadBalance;

import java.util.List;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/26 20:20
 */
public class SwimLaneLoadBalance implements LoadBalance {
    public static final String NAME = "swimlane";
    @Override
    public <T> Invoker<T> select(List<Invoker<T>> invokers, URL url, Invocation invocation) throws RpcException {
        if (CollectionUtils.isEmpty(invokers)) {
            return null;
        }
        if (invokers.size() == 1) {
            return invokers.get(0);
        }
        return doSelect(invokers, url, invocation);
    }

    protected <T> Invoker<T> doSelect(List<Invoker<T>> invokers, URL url, Invocation invocation){

        return invokers.get(0);
    }
}
