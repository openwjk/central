package com.openwjk.central.remote.dto;

import lombok.Data;

import java.util.Map;

/**
 * @author wangjunkai
 * @description
 * @date 2023/7/30 10:16
 */
@Data
public class Context {
    private Map<String,String> formParam;
    private Map<String,String> headParam;
    private Map<String,String> urlParam;
    private String bodyParam;
    private String url;
}
