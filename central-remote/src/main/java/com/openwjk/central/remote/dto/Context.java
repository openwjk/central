package com.openwjk.central.remote.dto;

import com.openwjk.central.remote.dto.request.RequestDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author wangjunkai
 * @description
 * @date 2023/7/30 10:16
 */
@Data
@NoArgsConstructor
public class Context {
    private RequestDTO requestDTO;
    private Object queryDTO;

    public Context(Object queryDTO) {
        this.queryDTO = queryDTO;
    }
}
