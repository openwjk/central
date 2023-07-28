package com.openwjk.central.service.enums;

/**
 * @author wangjunkai
 * @description 微信机器人
 * @date 2023/7/27 21:35
 */
public enum WeChatRobotEnum {
    WLCJDIYS("WLCJDIYS", CtConfigGroupEnum.COM_WE_CHAT_ROBOT, "群:万里长江第一帅>机器人"),
    ZY("ZY", CtConfigGroupEnum.COM_WE_CHAT_ROBOT, "群:只因>只因战士");

    WeChatRobotEnum(String code, CtConfigGroupEnum group, String desc) {
        this.code = code;
        this.group = group;
        this.desc = desc;
    }

    //编码
    private String code;
    //组编码
    private CtConfigGroupEnum group;
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

    public CtConfigGroupEnum getGroup() {
        return group;
    }

    public void setGroup(CtConfigGroupEnum group) {
        this.group = group;
    }
}
