package com.openwjk.central.commons.enums;

/**
 * @author wangjunkai
 * @description 定时任务
 * @date 2023/7/28 12:05
 */
public enum ScheduledTaskEnum {
    BIRTHDAY_REMINDER("BIRTHDAY_REMINDER",  "生日提醒定时任务"),
    FESTIVAL_OVERTIME("FESTIVAL_OVERTIME",  "节假日及补班提醒定时任务"),
    TODAY_REMINDER("TODAY_REMINDER",  "当日提醒定时任务"),
    BONUS("BONUS",  "福利"),
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
