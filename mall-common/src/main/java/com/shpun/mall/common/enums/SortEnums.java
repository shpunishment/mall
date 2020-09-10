package com.shpun.mall.common.enums;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/9/10 17:01
 */
public enum SortEnums {

    ASC(1, "顺序"),

    DESC(2, "逆序")

    ;

    private Integer value;

    private String name;

    SortEnums(Integer value, String name) {
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
