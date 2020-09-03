package com.shpun.mall.common.enums;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/9/3 10:15
 */
public enum MallCouponTimeTypeEnums {

    DAY(1, "天数"),

    PERIOD(2,"时间段"),

    ;

    private Integer value;

    private String name;

    MallCouponTimeTypeEnums(Integer value, String name) {
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
