package com.openwjk.central.remote.enums;

/**
 * @author wangjunkai
 * @description
 * @date 2023/7/30 10:02
 */
public enum RespTypeEnum {
    SUCCESS("SUCCESS","成功"),
    FAIL("FAIL","失败");

    RespTypeEnum(String code, String desc) {
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
