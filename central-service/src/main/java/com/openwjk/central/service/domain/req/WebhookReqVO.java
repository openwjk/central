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
 * @date 2023/8/5 9:49
 */
@ApiModel("webhook请求对象")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebhookReqVO extends BaseDomain {
    @ApiModelProperty(value = "key",required = true)
    private String key;
    @ApiModelProperty(value = "消息类型",required = true)
    private String msgType;
    @ApiModelProperty(value = "内容",required = true)
    private Content text;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Content extends BaseDomain{
        @ApiModelProperty(value = "正文",required = true)
        private String content;
    }
}
