package com.openwjk.central.remote.dto.request;

import com.openwjk.central.remote.enums.RemoteTypeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wangjunkai
 * @description
 * @date 2023/7/30 9:58
 */
@Data
public class CommonQueryReqDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private RemoteTypeEnum remoteTypeEnum;
    private Object queryDTO;
    private String cacheKey;


    public CommonQueryReqDTO(Object queryDTO, RemoteTypeEnum remoteTypeEnum) {
        this.queryDTO = queryDTO;
        this.remoteTypeEnum = remoteTypeEnum;
    }

}
