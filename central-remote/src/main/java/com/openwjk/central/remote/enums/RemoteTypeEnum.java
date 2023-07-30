package com.openwjk.central.remote.enums;

/**
 * @author wangjunkai
 * @description
 * @date 2023/7/30 9:59
 */
public enum RemoteTypeEnum {
    COM_WECHAT_TEXT_ROBOT("COM_WECHAT_TEXT_ROBOT","企业微信机器人发送文本消息"),
    COM_WECHAT_MARK_DOWN_ROBOT("COM_WECHAT_MARK_DOWN_ROBOT","企业微信机器人发送markdown消息");

    RemoteTypeEnum(String code, String desc) {
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