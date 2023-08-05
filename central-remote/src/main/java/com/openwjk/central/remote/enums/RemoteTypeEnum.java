package com.openwjk.central.remote.enums;

/**
 * @author wangjunkai
 * @description
 * @date 2023/7/30 9:59
 */
public enum RemoteTypeEnum {
    QW_TEXT_ROBOT("QW_TEXT_ROBOT","企业微信机器人发送文本消息"),
    QW_MARK_DOWN_ROBOT("QW_MARK_DOWN_ROBOT","企业微信机器人发送markdown消息"),
    QW_ACCESS_TOKEN("QW_ACCESS_TOKEN","企业微信accessToken"),
    QW_APP_NOTICE_TEXT_MSG("QW_APP_NOTICE_TEXT_MSG","企业微信应用：通知>发送文本消息"),
    QW_APP_NOTICE_MARKDOWN_MSG("QW_APP_NOTICE_MARKDOWN_MSG","企业微信应用：通知>发送文本消息");

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

    public static RemoteTypeEnum get(String code) {
        for (RemoteTypeEnum each : RemoteTypeEnum.values()) {
            if (each.code.equals(code)) {
                return each;
            }
        }
        return null;
    }
}
