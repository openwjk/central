package com.openwjk.central.service.domain.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangjunkai
 * @description
 * @date 2024/8/23 17:20
 */
@ApiModel("文件请求对象")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileReqVO {
    @ApiModelProperty(value = "业务编码",required = true)
    private String bizCode;
    @ApiModelProperty(value = "文件夹节点",required = true)
    private Long code;
    @ApiModelProperty(value = "content-Type",required = true)
    private String contentType;
    @ApiModelProperty(value = "文件名称",required = true)
    private String fileName;
}
