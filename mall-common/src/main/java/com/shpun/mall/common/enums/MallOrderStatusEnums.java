package com.shpun.mall.common.enums;

/**
 * @Description: 订单状态枚举
 * @Author: sun
 * @Date: 2020/8/28 15:17
 */
public enum MallOrderStatusEnums {

    CLOSE(-1, "已关闭"),

    WAIT2PAY(0, "待支付"),

    PAID(1,"已支付"),

    WAIT2RECEIVE(2,"待收货"),

    WAIT2COMMENT(3,"待评价"),

    COMPLETED(4,"已完成")

    ;

    private Integer value;

    private String name;

    MallOrderStatusEnums(Integer value, String name) {
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
