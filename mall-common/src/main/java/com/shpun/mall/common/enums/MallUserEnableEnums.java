package com.shpun.mall.common.enums;

/**
 * @Description: 用户可用枚举
 * @Author: sun
 * @Date: 2020/8/28 15:42
 */
public enum MallUserEnableEnums {

    UNABLE(0, "不可用"),

    ENABLE(1, "可用")

    ;

    private Integer value;

    private String name;

    MallUserEnableEnums(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

}
