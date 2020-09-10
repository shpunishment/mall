package com.shpun.mall.common.enums;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/9/10 17:06
 */
public enum MallDeliveryStatusEnums {

    WAIT2DELIVERY(1, "待配送"),

    DELIVERING(2, "配送中")

    ;

    private Integer value;

    private String name;

    MallDeliveryStatusEnums(Integer value, String name) {
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
