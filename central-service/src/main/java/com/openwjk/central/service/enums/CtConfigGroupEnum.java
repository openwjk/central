package com.openwjk.central.service.enums;

/**
 * @author wangjunkai
 * @description 配置组
 * @date 2023/7/27 21:35
 */
public enum CtConfigGroupEnum {
    COM_WE_CHAT_ROBOT("COM_WE_CHAT_ROBOT","企业微信机器人组"),
    COM_WE_CHAT_APP("COM_WE_CHAT_APP","企业微信应用组"),
    SCHEDULED_TASK("SCHEDULED_TASK","定时任务组");

    CtConfigGroupEnum(String code, String desc) {
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
