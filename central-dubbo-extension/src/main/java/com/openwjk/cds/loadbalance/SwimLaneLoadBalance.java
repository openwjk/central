package com.openwjk.cds.loadbalance;

import com.openwjk.cds.utils.SwimLaneUtil;
import org.apache.dubbo.common.Node;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.logger.Logger;
import org.apache.dubbo.common.logger.LoggerFactory;
import org.apache.dubbo.common.utils.CollectionUtils;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.cluster.LoadBalance;
import org.apache.dubbo.rpc.cluster.loadbalance.AbstractLoadBalance;
import org.apache.dubbo.rpc.model.ApplicationModel;
import org.apache.dubbo.rpc.model.ScopeModelUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.dubbo.common.constants.CommonConstants.DEFAULT_LOADBALANCE;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/26 20:20
 */
public class SwimLaneLoadBalance extends AbstractLoadBalance {
    public static final String NAME = "swimlane";
    private static final Logger logger = LoggerFactory.getLogger(SwimLaneLoadBalance.class);
    protected <T> Invoker<T> doSelect(List<Invoker<T>> invokers, URL url, Invocation invocation) {
        String swimLaneNo = SwimLaneUtil.getSwimLaneNo();
        String swimLog = SwimLaneUtil.getSwimLog();
        List<String> hostList = invokers.stream().map(Node::getUrl).collect(Collectors.toList()).stream().map(URL::getHost).collect(Collectors.toList());
        if (StringUtils.isEmpty(swimLog) || !"false".equals(swimLog)) {
            logger.info("load balance start, interface:" + ((Invoker) invokers.get(0)).getInterface().getName() + ", all hostList:" + String.join(",", hostList) + " , swimLaneNo:" + swimLaneNo);
        }

        List<Invoker<T>> mainLaneInvokers = new ArrayList();
        Iterator<Invoker<T>> mainIterator = invokers.iterator();

        while (mainIterator.hasNext()) {
            Invoker<T> invoker = mainIterator.next();
            if (StringUtils.isBlank(invoker.getUrl().getParameter("SWIM_LANE_NO"))) {
                mainLaneInvokers.add(invoker);
            }
        }

        if (StringUtils.isBlank(swimLaneNo)) {
            return getInvoker(invokers, url, invocation, swimLaneNo, swimLog, mainLaneInvokers);
        } else {
            List<Invoker<T>> thisLaneInvokers = new ArrayList();
            Iterator<Invoker<T>> thisLaneIterator = invokers.iterator();

            while (thisLaneIterator.hasNext()) {
                Invoker<T> invoker = thisLaneIterator.next();
                if (swimLaneNo.equals(invoker.getUrl().getParameter("SWIM_LANE_NO"))) {
                    thisLaneInvokers.add(invoker);
                }
            }

            if (thisLaneInvokers.size() == 0) {
                return getInvoker(invokers, url, invocation, swimLaneNo, swimLog, mainLaneInvokers);
            } else {
                return getInvoker(invokers, url, invocation, swimLaneNo, swimLog, thisLaneInvokers);
            }
        }
    }


    private <T> Invoker<T> getInvoker(List<Invoker<T>> invokers, URL url, Invocation invocation, String swimLaneNo, String swimLog, List<Invoker<T>> mainLaneInvokers) {
        if (CollectionUtils.isEmpty(invokers)) {
            logger.error(String.format("load balance end, swimLaneNo:%s have not invoker", swimLaneNo));
            return null;
        }
        List<String> hostList = mainLaneInvokers.stream().map(Node::getUrl).collect(Collectors.toList()).stream().map(URL::getHost).collect(Collectors.toList());
        if (StringUtils.isEmpty(swimLog) || !"false".equals(swimLog)) {
            logger.info("load balance end, interface:" + ((Invoker) invokers.get(0)).getInterface().getName() + ", optional hostList:" + String.join(",", hostList) + " , swimLaneNo:" + swimLaneNo);
        }

        return getRandomLoadBalance(invocation).select(mainLaneInvokers, url, invocation);
    }

    private LoadBalance getRandomLoadBalance(Invocation invocation) {
        ApplicationModel applicationModel = ScopeModelUtil.getApplicationModel(invocation.getModuleModel());
        return applicationModel.getExtensionLoader(LoadBalance.class).getExtension(DEFAULT_LOADBALANCE);
    }
}
