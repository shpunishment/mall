package com.shpun.mall.common.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/9/2 17:37
 */
@ApiModel
public class MallCouponVo implements Serializable {

    @ApiModelProperty("优惠券id")
    private Integer couponId;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("描述")
    private String desc;

    @ApiModelProperty("最低消费")
    private BigDecimal minPrice;

    @ApiModelProperty("最低消费str")
    private String minPriceStr;

    @ApiModelProperty("优惠金额")
    private BigDecimal discount;

    @ApiModelProperty("优惠金额str")
    private String discountStr;

    @ApiModelProperty("限领数，默认1")
    private Integer limit;

    @ApiModelProperty("数量，-1无限制")
    private Integer total;

    @ApiModelProperty("数量标识")
    private Boolean hasTotal;

    @ApiModelProperty("类型，1通用券，2新人礼券")
    private Integer type;

    @ApiModelProperty("使用类型，1全场，2指定分类，3指定商品")
    private Integer useType;

    @ApiModelProperty("生效时间类型，1天数，2时间段")
    private Integer timeType;

    @ApiModelProperty("有效期限，自领取后几天内有效，0当天")
    private Integer days;

    @ApiModelProperty("有效期限，开始时间。（亦作为用户优惠券的有效期开始时间）")
    private Date startTime;

    @ApiModelProperty("有效期限，结束时间。（亦作为用户优惠券的有效期结束时间）")
    private Date endTime;

    @ApiModelProperty("已领取标识")
    private Boolean received;

    @ApiModelProperty("用户领取时间")
    private Date userReceivedTime;

    @ApiModelProperty("用户优惠券状态")
    private Integer userCouponStatus;

    @ApiModelProperty("订单信息，只包含价格")
    private MallOrderVo orderVo;

    private static final long serialVersionUID = 1L;

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public String getMinPriceStr() {
        return minPriceStr;
    }

    public void setMinPriceStr(String minPriceStr) {
        this.minPriceStr = minPriceStr;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getDiscountStr() {
        return discountStr;
    }

    public void setDiscountStr(String discountStr) {
        this.discountStr = discountStr;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Boolean getHasTotal() {
        return hasTotal;
    }

    public void setHasTotal(Boolean hasTotal) {
        this.hasTotal = hasTotal;
    }

    public Integer getUseType() {
        return useType;
    }

    public void setUseType(Integer useType) {
        this.useType = useType;
    }

    public Integer getTimeType() {
        return timeType;
    }

    public void setTimeType(Integer timeType) {
        this.timeType = timeType;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Boolean getReceived() {
        return received;
    }

    public void setReceived(Boolean received) {
        this.received = received;
    }

    public Date getUserReceivedTime() {
        return userReceivedTime;
    }

    public void setUserReceivedTime(Date userReceivedTime) {
        this.userReceivedTime = userReceivedTime;
    }

    public Integer getUserCouponStatus() {
        return userCouponStatus;
    }

    public void setUserCouponStatus(Integer userCouponStatus) {
        this.userCouponStatus = userCouponStatus;
    }

    public MallOrderVo getOrderVo() {
        return orderVo;
    }

    public void setOrderVo(MallOrderVo orderVo) {
        this.orderVo = orderVo;
    }
}
