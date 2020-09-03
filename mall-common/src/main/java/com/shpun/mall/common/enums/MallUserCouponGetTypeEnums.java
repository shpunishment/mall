package com.shpun.mall.common.enums;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/9/3 10:39
 */
public enum MallUserCouponGetTypeEnums {

    INITIATIVE(1, "主动领取"),

    BACKSTAGE(2,"后台赠送"),

    ;

    private Integer value;

    private String name;

    MallUserCouponGetTypeEnums(Integer value, String name) {
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
