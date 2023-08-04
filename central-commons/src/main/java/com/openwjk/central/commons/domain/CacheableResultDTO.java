package com.openwjk.central.commons.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/1 14:33
 */
@Data
public class CacheableResultDTO<E extends Serializable> implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long expire;
    private Boolean enterCache;
    private Boolean fromCache;
    private E entity;

    public CacheableResultDTO() {
        this.enterCache = Boolean.FALSE;
        this.fromCache = Boolean.FALSE;
    }

    public CacheableResultDTO(E entity) {
        this.entity = entity;
    }
}
