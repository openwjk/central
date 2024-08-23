package com.openwjk.central.service.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangjunkai
 * @description
 * @date 2024/8/22 18:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OssFile {
    private String bucket;
    private String md5;
    private String originName;
    private String objectName;
}
