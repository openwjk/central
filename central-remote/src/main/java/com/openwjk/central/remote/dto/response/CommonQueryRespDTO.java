package com.openwjk.central.remote.dto.response;

import com.openwjk.central.remote.enums.RespTypeEnum;

import java.io.Serializable;

/**
 * @author wangjunkai
 * @description
 * @date 2023/7/30 10:01
 */
public class CommonQueryRespDTO<E> implements Serializable {
    private static final long serialVersionUID = 1L;

    private RespTypeEnum respType;
    private E respEntry;

    public CommonQueryRespDTO(RespTypeEnum respType, E respEntry) {
        this.respType = respType;
        this.respEntry = respEntry;
    }

    public CommonQueryRespDTO(RespTypeEnum respType) {
        this.respType = respType;
    }

    public CommonQueryRespDTO() {
        this.respType = RespTypeEnum.SUCCESS;
    }

    public RespTypeEnum getRespType() {
        return respType;
    }

    public void setRespType(RespTypeEnum respType) {
        this.respType = respType;
    }

    public E getRespEntry() {
        return respEntry;
    }

    public void setRespEntry(E respEntry) {
        this.respEntry = respEntry;
    }
}
