package com.shpun.mall.common.enums;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/9/3 17:51
 */
public enum MallUserCouponStatusEnums {

    UNUSED(1, "未使用"),

    USED(2,"已使用"),

    EXPIRED(3,"已过期"),

    ;

    private Integer value;

    private String name;

    MallUserCouponStatusEnums(Integer value, String name) {
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
