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
@NoArgsConstructor
public class CacheableResultDTO<E extends Serializable> implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long expire;
    private Boolean enterCache;
    private E entity;

    public CacheableResultDTO(E entity) {
        this.entity = entity;
    }
}
