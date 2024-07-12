package com.openwjk.central.service.domain.req;

import com.openwjk.central.commons.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangjunkai
 * @description
 * @date 2024/8/5 9:49
 */
@ApiModel("配置请求对象")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CtConfigReqVO extends BaseDomain {
    @ApiModelProperty(value = "groupCode",required = true)
    private String groupCode;
    @ApiModelProperty(value = "groupName",required = true)
    private String groupName;
    @ApiModelProperty(value = "code",required = true)
    private String code;
    @ApiModelProperty(value = "value",required = true)
    private String value;
}
