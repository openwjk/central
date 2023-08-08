package com.openwjk.central.commons.enums;

/**
 * @author wangjunkai
 * @description 企业微信应用
 * @date 2023/7/27 21:35
 */
public enum QwAppMsgTypeEnum {
    TEXT("TEXT", "text","通知"),
    MARKDOWN("MARKDOWN", "markdown","通知");

    QwAppMsgTypeEnum(String code, String value, String desc) {
        this.code = code;
        this.value = value;
        this.desc = desc;
    }

    //编码
    private String code;
    //值
    private String value;
    //描述
    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
