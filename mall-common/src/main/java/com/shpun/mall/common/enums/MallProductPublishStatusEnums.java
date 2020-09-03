package com.shpun.mall.common.enums;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/28 15:56
 */
public enum MallProductPublishStatusEnums {

    ON(0, "上架"),

    OFF(1, "下架")

    ;

    private Integer value;

    private String name;

    MallProductPublishStatusEnums(Integer value, String name) {
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
