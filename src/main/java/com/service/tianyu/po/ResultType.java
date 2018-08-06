package com.service.tianyu.po;

/**
 * Created by intron on 2017/1/15 0015.
 */
public enum ResultType {
    RESULT_CLOSE_CLOSED("关停"),
    RESULT_CLOSE_OPENED("开启"),
    RESULT_CLOSE_NO_SQL_DATA("数据库中无信息"),
    RESULT_CLOSE_NO_IP_DATA("无法获取IP信息，或IP库中查不到");

    private String desc;

    public static final String RESULT_CONTROL_DEFAULT = "9999";

    ResultType(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
