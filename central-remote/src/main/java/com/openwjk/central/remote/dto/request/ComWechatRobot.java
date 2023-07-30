package com.openwjk.central.remote.dto.request;

import com.openwjk.central.commons.enums.ComWeChatRobotEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wangjunkai
 * @description
 * @date 2023/7/30 10:49
 */
@Data
public class ComWechatRobot implements Serializable {
    private static final long serialVersionUID = 1L;
    private String verbalTrick;
    private ComWeChatRobotEnum robotEnum;
}
