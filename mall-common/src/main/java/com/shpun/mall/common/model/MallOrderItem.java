package com.shpun.mall.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description: 订单商品
 * @Author: sun
 * @Date: 2020/8/22 10:34
 */
@ApiModel
public class MallOrderItem implements Serializable {

    @ApiModelProperty("订单商品id")
    private Integer orderItemId;

    @ApiModelProperty("订单id")
    private Integer orderId;

    @ApiModelProperty("订单编号")
    private String orderNumber;

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

    @ApiModelProperty("限时抢购商品id")
    private Integer flashItemId;

    private static final long serialVersionUID = 1L;

    public Integer getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Integer orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

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

    public Integer getFlashItemId() {
        return flashItemId;
    }

    public void setFlashItemId(Integer flashItemId) {
        this.flashItemId = flashItemId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderItemId=").append(orderItemId);
        sb.append(", orderId=").append(orderId);
        sb.append(", orderNumber=").append(orderNumber);
        sb.append(", productId=").append(productId);
        sb.append(", productName=").append(productName);
        sb.append(", pic=").append(pic);
        sb.append(", price=").append(price);
        sb.append(", quantity=").append(quantity);
        sb.append(", flashItemId=").append(flashItemId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}