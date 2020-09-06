package com.shpun.mall.common.enums;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/9/3 9:29
 */
public enum MallCouponUseTypeEnums {

    ALL(1, "全场"),

    CLASSIFY(2, "指定分类"),

    PRODUCT(3,"指定商品"),

    ;

    private Integer value;

    private String name;

    MallCouponUseTypeEnums(Integer value, String name) {
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
