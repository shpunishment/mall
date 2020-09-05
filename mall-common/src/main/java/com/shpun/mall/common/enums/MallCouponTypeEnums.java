package com.shpun.mall.common.enums;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/9/5 20:40
 */
public enum MallCouponTypeEnums {

    UNIVERSAL(1, "通用券"),

    NEW_USER(2,"新人礼券"),

    ;

    private Integer value;

    private String name;

    MallCouponTypeEnums(Integer value, String name) {
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
