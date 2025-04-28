package com.openwjk.central.service.domain.resp;

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
public class WxminiPhotoVO {
    @ApiModelProperty(value = "id",required = true)
    private Long id;
    @ApiModelProperty(value = "文件名称",required = true)
    private String fileName;
    @ApiModelProperty(value = "url",required = true)
    private String url;
}
