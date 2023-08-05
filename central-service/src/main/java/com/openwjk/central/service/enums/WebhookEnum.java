package com.openwjk.central.service.enums;

/**
 * @author wangjunkai
 * @description
 * @date 2023/7/30 9:59
 */
public enum WebhookEnum {
    AE1A71CEAB454A428B6AB5E29999DCFD("AE1A71CEAB454A428B6AB5E29999DCFD","DDNS>地址变更提醒");

    WebhookEnum(String code, String desc) {
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

    public static WebhookEnum get(String code) {
        for (WebhookEnum each : WebhookEnum.values()) {
            if (each.code.equals(code)) {
                return each;
            }
        }
        return null;
    }

    public static Boolean contains(String code) {
        for (WebhookEnum each : WebhookEnum.values()) {
            if (each.code.equals(code)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }
}
