package com.openwjk.central.commons.utils;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.openwjk.central.commons.domain.LoginAccountDTO;
import org.apache.logging.log4j.ThreadContext;

import java.util.concurrent.ConcurrentHashMap;


public class ThreadLocalUtil {
    private static final TransmittableThreadLocal<LoginAccountDTO> LOCAL_LOGIN_ACCOUNT_DTO = new TransmittableThreadLocal<>();

    private static final TransmittableThreadLocal<ConcurrentHashMap<String, String>> LOCAL_PARAMS = new TransmittableThreadLocal<>();

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
        ThreadContext.put(Constants.LOCAL_REQ_PARAM, reqParam);
    }

    public static String getReqParamAfterTransform() {
        return ThreadContext.get(Constants.LOCAL_REQ_PARAM);
    }

    public static void removeReqParamAfterTransform() {
        ThreadContext.remove(Constants.LOCAL_REQ_PARAM);
    }

    public static void setLoginDto(LoginAccountDTO loginDto) {
        LOCAL_LOGIN_ACCOUNT_DTO.set(loginDto);
    }

    public static LoginAccountDTO getLoginDto() {
        return LOCAL_LOGIN_ACCOUNT_DTO.get();
    }

    public static void removeLoginDto() {
        LOCAL_LOGIN_ACCOUNT_DTO.remove();
    }

    public static void setLocalParams(String key, String value) {
        LOCAL_PARAMS.get().put(key, value);
    }

    public static ConcurrentHashMap<String, String> getLocalParams() {
        return LOCAL_PARAMS.get();
    }

    public static String getLocalParamsByKey(String key) {
        return LOCAL_PARAMS.get().get(key);
    }

    public static void removeLocalParamsByKey(String key) {
        LOCAL_PARAMS.get().remove(key);
    }

    public static void removeLocalParams() {
        LOCAL_PARAMS.remove();
    }

}
