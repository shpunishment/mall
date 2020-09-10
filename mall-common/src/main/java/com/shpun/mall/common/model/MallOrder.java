package com.shpun.mall.common.model;

import com.shpun.mall.common.model.vo.MallOrderVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: 订单
 * @Author: sun
 * @Date: 2020/8/22 10:34
 */
@ApiModel
public class MallOrder implements Serializable {

    @NotNull(groups = Comment.class)
    @Min(value = 1, groups = Comment.class)
    @Max(value = 2147483647, groups = Comment.class)
    @ApiModelProperty("订单id")
    private Integer orderId;

    @ApiModelProperty("订单编号")
    private String orderNumber;

    @ApiModelProperty("下单时间")
    private Date orderTime;

    @ApiModelProperty("订单关闭时间")
    private Date endTime;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("优惠券id")
    private Integer couponId;

    @ApiModelProperty("收货地址id")
    private Integer addressId;

    @ApiModelProperty("收货人")
    private String receiveName;

    @ApiModelProperty("收货电话")
    private String receivePhone;

    @ApiModelProperty("收货地址")
    private String receiveAddress;

    @ApiModelProperty("商品费用")
    private BigDecimal productPrice;

    @ApiModelProperty("配送费用")
    private BigDecimal deliveryPrice;

    @ApiModelProperty("优惠券抵扣金额")
    private BigDecimal couponPrice;

    @ApiModelProperty("订单金额")
    private BigDecimal totalPrice;

    @ApiModelProperty("订单状态，-1已关闭；0待支付；1已支付；2待收货；3待评价；4已完成")
    private Integer status;

    @ApiModelProperty("订单备注")
    private String remark;

    @ApiModelProperty("期望送达时间，如尽快送达，X月X日 14:00~15:00")
    private String expectTime;

    @ApiModelProperty("商品总数")
    private Integer productAmount;

    @ApiModelProperty("支付方式，0未支付，1支付宝，2微信")
    private Integer payType;

    @ApiModelProperty("支付编号")
    private String payNumber;

    @ApiModelProperty("支付时间")
    private Date payTime;

    @ApiModelProperty("配送员id")
    private Integer deliveryId;

    @ApiModelProperty("发货时间")
    private Date deliveryTime;

    @ApiModelProperty("收货时间")
    private Date receiveTime;

    @NotNull(groups = Comment.class)
    @Min(value = 1, groups = Comment.class)
    @Max(value = 5, groups = Comment.class)
    @ApiModelProperty("评分，1~5分")
    private Integer score;

    @ApiModelProperty("评价")
    private String comment;

    @ApiModelProperty("评价时间")
    private Date commentTime;

    @ApiModelProperty("删除标识，0未删除，1删除")
    private Integer deleted;

    private static final long serialVersionUID = 1L;

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

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public BigDecimal getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(BigDecimal deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public BigDecimal getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(BigDecimal couponPrice) {
        this.couponPrice = couponPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getExpectTime() {
        return expectTime;
    }

    public void setExpectTime(String expectTime) {
        this.expectTime = expectTime;
    }

    public Integer getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(Integer productAmount) {
        this.productAmount = productAmount;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getPayNumber() {
        return payNumber;
    }

    public void setPayNumber(String payNumber) {
        this.payNumber = payNumber;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Integer getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Integer deliveryId) {
        this.deliveryId = deliveryId;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderId=").append(orderId);
        sb.append(", orderNumber=").append(orderNumber);
        sb.append(", orderTime=").append(orderTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", userId=").append(userId);
        sb.append(", couponId=").append(couponId);
        sb.append(", addressId=").append(addressId);
        sb.append(", receiveName=").append(receiveName);
        sb.append(", receivePhone=").append(receivePhone);
        sb.append(", receiveAddress=").append(receiveAddress);
        sb.append(", productPrice=").append(productPrice);
        sb.append(", deliveryPrice=").append(deliveryPrice);
        sb.append(", couponPrice=").append(couponPrice);
        sb.append(", totalPrice=").append(totalPrice);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", expectTime=").append(expectTime);
        sb.append(", productAmount=").append(productAmount);
        sb.append(", payType=").append(payType);
        sb.append(", payNumber=").append(payNumber);
        sb.append(", payTime=").append(payTime);
        sb.append(", deliveryId=").append(deliveryId);
        sb.append(", deliveryTime=").append(deliveryTime);
        sb.append(", receiveTime=").append(receiveTime);
        sb.append(", score=").append(score);
        sb.append(", comment=").append(comment);
        sb.append(", commentTime=").append(commentTime);
        sb.append(", deleted=").append(deleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 评价订单分组
     */
    public interface Comment {
    }

}