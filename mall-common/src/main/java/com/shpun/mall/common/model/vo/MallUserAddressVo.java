package com.shpun.mall.common.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/23 18:15
 */
@ApiModel
public class MallUserAddressVo implements Serializable {

    @ApiModelProperty("收货地址id")
    private Integer addressId;

    @ApiModelProperty("收货人")
    private String name;

    @ApiModelProperty("性别，0无，1先生，2女士")
    private Integer sex;

    @ApiModelProperty("收货电话")
    private String phone;

    @ApiModelProperty("收货地址")
    private String address;

    @ApiModelProperty("地址类型，0未选择，1家，2父母家，3朋友家，4公司，5学校")
    private Integer type;

    private static final long serialVersionUID = 1L;

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
