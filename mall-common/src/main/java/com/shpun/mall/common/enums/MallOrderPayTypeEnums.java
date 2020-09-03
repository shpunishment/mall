package com.shpun.mall.common.enums;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/28 15:52
 */
public enum MallOrderPayTypeEnums {

    UNPAID(0, "未支付"),

    ALI(1, "支付宝"),

    WECHAT(2, "微信")

    ;

    private Integer value;

    private String name;

    MallOrderPayTypeEnums(Integer value, String name) {
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
