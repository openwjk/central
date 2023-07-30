package com.openwjk.central.service.enums;

/**
 * @author wangjunkai
 * @description 定时任务
 * @date 2023/7/28 12:05
 */
public enum ScheduledTaskEnum {
    BIRTHDAY_REMINDER("BIRTHDAY_REMINDER",  "生日提醒定时任务"),
    ;

    ScheduledTaskEnum(String code, String desc) {
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

    public static ScheduledTaskEnum get(String code) {
        for (ScheduledTaskEnum each : ScheduledTaskEnum.values()) {
            if (each.code.equals(code)) {
                return each;
            }
        }
        return null;
    }
}