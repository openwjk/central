package com.openwjk.central.web.domain;

import com.openwjk.central.service.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author wangjunkai
 * @description
 * @date 2023/7/28 14:03
 */
@Data
@ApiModel("测试实体")
public class SystemDomain extends BaseDomain {
    @ApiModelProperty("参数")
    private List<String> args;
    @ApiModelProperty("话术")
    private String verbalTrick;
}
