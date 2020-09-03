package com.shpun.mall.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/31 11:26
 */
public enum MallUserAddressSexEnums {

    NO(0, "无"),

    MAN(1, "先生"),

    WOMAN(2, "女士")

    ;

    private Integer value;

    private String name;

    static Map<Integer, MallUserAddressSexEnums> enumMap = new HashMap<>(3);
    static{
        for(MallUserAddressSexEnums type : MallUserAddressSexEnums.values()){
            enumMap.put(type.getValue(), type);
        }
    }


    MallUserAddressSexEnums(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static MallUserAddressSexEnums getEnum(Integer value) {
        return enumMap.get(value);
    }

}
