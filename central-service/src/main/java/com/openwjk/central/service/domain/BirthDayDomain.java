package com.openwjk.central.service.domain;

import lombok.Data;

import java.util.List;

/**
 * @author wangjunkai
 * @description
 * @date 2023/7/28 14:03
 */
@Data
public class BirthDayDomain extends BaseDomain {
    private List<String> args;
    private String verbalTrick;
}
