package com.openwjk.central.remote.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Map<String,String> formParam;
    private Map<String,String> headParam;
    private Map<String,String> urlParam;
    private String bodyParam;
    private String url;
}
