package com.openwjk.central.remote.dto.response;

import com.openwjk.central.commons.enums.ComWeChatRobotEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author wangjunkai
 * @description
 * @date 2023/7/30 10:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComWechatRobotRespDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String errcode;
    private String errmsg;
}
