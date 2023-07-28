package com.openwjk.central.service.enums;

/**
 * @author wangjunkai
 * @description 定时任务
 * @date 2023/7/28 12:05
 */
public enum ScheduledEnum {
    BIRTHDAY_REMINDER("BIRTHDAY_REMINDER", CtConfigGroupEnum.SCHEDULED_TASK, "生日提醒定时任务"),
    ;

    ScheduledEnum(String code, CtConfigGroupEnum group, String desc) {
        this.code = code;
        this.group = group;
        this.desc = desc;
    }

    private String code;
    private CtConfigGroupEnum group;
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

    public CtConfigGroupEnum getGroup() {
        return group;
    }

    public void setGroup(CtConfigGroupEnum group) {
        this.group = group;
    }

    public static ScheduledEnum get(String code) {
        for (ScheduledEnum each : ScheduledEnum.values()) {
            if (each.code.equals(code)) {
                return each;
            }
        }
        return null;
    }
}
