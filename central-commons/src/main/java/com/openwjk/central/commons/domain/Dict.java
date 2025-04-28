package com.openwjk.central.commons.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author junkai.wang
 * @date 2025/4/24 15:07
 * @description TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dict extends BaseDomain {
    private String code;
    private String desc;
}
