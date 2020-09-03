package com.shpun.mall.common.enums;

/**
 * @Description: 用户搜索历史类型
 * @Author: sun
 * @Date: 2020/8/28 16:00
 */
public enum MallUserSearchHistoryTypeEnums {

    PRODUCT(1, "商品"),

    ORDER(2, "订单")

    ;

    private Integer value;

    private String name;

    MallUserSearchHistoryTypeEnums(Integer value, String name) {
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
