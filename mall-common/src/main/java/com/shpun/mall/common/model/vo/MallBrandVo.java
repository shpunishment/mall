package com.shpun.mall.common.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/23 18:12
 */
@ApiModel
public class MallBrandVo implements Serializable {

    @ApiModelProperty("品牌id")
    private Integer brandId;

    @ApiModelProperty("品牌名称")
    private String brandName;

    private static final long serialVersionUID = 1L;

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
