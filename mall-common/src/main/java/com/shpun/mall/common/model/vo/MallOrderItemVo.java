package com.shpun.mall.common.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/23 18:13
 */
@ApiModel
public class MallOrderItemVo implements Serializable {

    @ApiModelProperty("商品id")
    private Integer productId;

    @ApiModelProperty("商品名称")
    private String productName;

    @ApiModelProperty("商品图片，为文件id")
    private Integer pic;

    @ApiModelProperty("商品单价")
    private BigDecimal price;

    @ApiModelProperty("数量")
    private Integer quantity;

    private static final long serialVersionUID = 1L;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getPic() {
        return pic;
    }

    public void setPic(Integer pic) {
        this.pic = pic;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
