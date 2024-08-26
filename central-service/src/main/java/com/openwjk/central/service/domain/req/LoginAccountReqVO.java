package com.openwjk.central.service.domain.req;

import com.openwjk.central.commons.annotation.ParamCondition;
import com.openwjk.central.commons.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/15 9:46
 */
@Data
@ApiModel("账号实体")
public class LoginAccountReqVO extends BaseDomain {
    @ApiModelProperty(value = "账号", required = true)
    @ParamCondition(notEmpty = true)
    private String account;
    @ApiModelProperty(value = "密码", required = true)
    private String password;
}
