package com.shpun.mall.common.model.vo;

import com.shpun.mall.common.model.MallCart;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/23 18:13
 */
@ApiModel
public class MallOrderVo implements Serializable {

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

    @NotNull(groups = Generate.class)
    @Min(value = 1, groups = Generate.class)
    @Max(value = 2147483647, groups = Generate.class)
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

    @ApiModelProperty("订单状态，-1已关闭；0未关闭，待支付；1已支付，待收货；2已收货，待评价；3已评价，已完成")
    private Integer status;

    @ApiModelProperty("订单备注")
    private String remark;

    @NotBlank(groups = Generate.class)
    @Length(min = 4, max = 20, groups = Generate.class)
    @ApiModelProperty("期望送达时间，如尽快送达，X月X日 14:00~15:00")
    private String expectTime;

    @ApiModelProperty("商品总数")
    private Integer productAmount;

    @NotNull(groups = Generate.class)
    @Min(value = 1, groups = Generate.class)
    @Max(value = 2, groups = Generate.class)
    @ApiModelProperty("支付方式，0未支付，1支付宝，2微信")
    private Integer payType;

    @ApiModelProperty("支付时间")
    private Date payTime;

    @ApiModelProperty("配送员id")
    private Integer deliveryId;

    @ApiModelProperty("配送员")
    private String deliveryMan;

    @ApiModelProperty("收货时间")
    private Date receiveTime;

    @ApiModelProperty("评分，1~5分")
    private Integer score;

    @ApiModelProperty("评价")
    private String comment;

    @ApiModelProperty("评价时间")
    private Date commentTime;

    @ApiModelProperty("订单商品")
    private List<MallOrderItemVo> orderItemVoList;

    @ApiModelProperty("购物车idList")
    private List<Integer> cartIdList;

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

    public String getDeliveryMan() {
        return deliveryMan;
    }

    public void setDeliveryMan(String deliveryMan) {
        this.deliveryMan = deliveryMan;
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

    public List<MallOrderItemVo> getOrderItemVoList() {
        return orderItemVoList;
    }

    public void setOrderItemVoList(List<MallOrderItemVo> orderItemVoList) {
        this.orderItemVoList = orderItemVoList;
    }

    public List<Integer> getCartIdList() {
        return cartIdList;
    }

    public void setCartIdList(List<Integer> cartIdList) {
        this.cartIdList = cartIdList;
    }

    /**
     * 下单校验分组
     */
    public interface Generate {
    }

}
