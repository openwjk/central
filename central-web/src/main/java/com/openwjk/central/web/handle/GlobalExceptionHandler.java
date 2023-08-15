package com.openwjk.central.web.handle;

import com.openwjk.commons.domain.ResponseVO;
import com.openwjk.commons.enums.ResponseEnum;
import com.openwjk.commons.exception.ParamInvalidException;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/15 15:13
 */
@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {
    /**
     * 兜底异常，返回系统异常
     *
     * @param req
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseVO defaultErrorHandler(HttpServletRequest req, Exception e) {
        log.error(String.format("call url:%s, occur exception. e.getMessage:%s.", req.getRequestURL(), e.getMessage()), e);
        return new ResponseVO(ResponseEnum.SYSTEM_ERROR);
    }


    /**
     * 参数校验错误，返回参数无效
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = ParamInvalidException.class)
    @ResponseBody
    public ResponseVO paramInvalidException(HttpServletRequest req, ParamInvalidException e) {
        String paramName = e.getParamName();
        Object paramValue = e.getParamValue();
        String responseMsg = e.getResponseMsg();
        log.warn(String.format("call url:%s, param invalid. paramName:%s, paramValue:%s, e.getMessage:%s."
                , req.getRequestURL(), paramName, paramValue, e.getMessage()), e);
        if (StringUtils.isNotEmpty(responseMsg)) {
            return new ResponseVO(ResponseEnum.PARAM_CHECK_FAIL.getCode(), responseMsg);
        }
        if (StringUtils.isNotEmpty(paramName)) {
            return new ResponseVO(ResponseEnum.PARAM_CHECK_FAIL.getCode()
                    , String.format("value invalid, param name:[%s], value:[%s]", paramName, paramValue));
        } else {
            return new ResponseVO(ResponseEnum.PARAM_CHECK_FAIL);
        }
    }
}
