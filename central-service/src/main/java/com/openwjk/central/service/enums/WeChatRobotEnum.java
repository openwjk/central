package com.openwjk.central.service.enums;

/**
 * @author wangjunkai
 * @description
 * @date 2023/7/27 21:35
 */
public enum WeChatRobotEnum {
    WLCJDIYS("WLCJDIYS", "群:万里长江第一帅");

    WeChatRobotEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private String code;
    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
