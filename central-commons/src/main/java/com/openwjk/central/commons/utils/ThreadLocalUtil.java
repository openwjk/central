package com.openwjk.central.commons.utils;

import org.apache.logging.log4j.ThreadContext;

/**
 * Created by shiwei.yan on 2018/2/6.
 */
public class ThreadLocalUtil {

    public static void setLogId(String logId) {
        ThreadContext.put(Constants.LOCAL_REQ_LOG_ID, logId);
    }

    public static String getLogId() {
        return ThreadContext.get(Constants.LOCAL_REQ_LOG_ID);
    }

    public static void removeLogId() {
        ThreadContext.remove(Constants.LOCAL_REQ_LOG_ID);
    }

    public static void setReqParamAfterTransform(String reqParam) {
        ThreadContext.put(Constants.REQ_PARAM, reqParam);
    }

    public static String getReqParamAfterTransform() {
        return ThreadContext.get(Constants.REQ_PARAM);
    }

    public static void removeReqParamAfterTransform() {
        ThreadContext.remove(Constants.REQ_PARAM);
    }

}
