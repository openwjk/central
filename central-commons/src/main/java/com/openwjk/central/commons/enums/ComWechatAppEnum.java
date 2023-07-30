package com.openwjk.central.commons.enums;

/**
 * @author wangjunkai
 * @description 微信机器人
 * @date 2023/7/27 21:35
 */
public enum ComWechatAppEnum {
    NOTIFICATION("NOTIFICATION", "通知");

    ComWechatAppEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    //编码
    private String code;
    //描述
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
