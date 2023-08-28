package com.openwjk.cds.registry;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.utils.Holder;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.cluster.router.state.AbstractStateRouter;
import org.apache.dubbo.rpc.cluster.router.state.BitList;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/28 16:23
 */
public class SwimLaneStateRouter extends AbstractStateRouter {
    public SwimLaneStateRouter(URL url) {
        super(url);
    }

    @Override
    protected BitList<Invoker> doRoute(BitList bitList, URL url, Invocation invocation, boolean needToPrintMessage, Holder holder, Holder messageHolder) throws RpcException {
        return null;
    }

    @Override
    public void stop() {
        super.stop();
    }
}
