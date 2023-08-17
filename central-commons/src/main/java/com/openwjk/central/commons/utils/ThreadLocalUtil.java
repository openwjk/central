package com.openwjk.central.commons.utils;

import com.openwjk.central.commons.domain.LoginAccountDTO;
import org.apache.logging.log4j.ThreadContext;


public class ThreadLocalUtil {
    private static final ThreadLocal<LoginAccountDTO> LOCAL_LOGIN_ACCOUNT_DTO = new ThreadLocal<>();

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

}
