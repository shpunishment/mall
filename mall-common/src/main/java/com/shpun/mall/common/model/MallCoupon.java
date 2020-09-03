package com.shpun.mall.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel
public class MallCoupon implements Serializable {

    @ApiModelProperty("优惠券id")
    private Integer couponId;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("创建管理员id")
    private Integer createId;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("更新管理员id")
    private Integer updateId;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("描述")
    private String desc;

    @ApiModelProperty("最低消费")
    private BigDecimal minPrice;

    @ApiModelProperty("优惠金额")
    private BigDecimal discount;

    @ApiModelProperty("限领数，默认1")
    private Integer limit;

    @ApiModelProperty("数量，-1无限制")
    private Integer total;

    @ApiModelProperty("状态，0禁用，1启用")
    private Integer status;

    @ApiModelProperty("类型，1通用券，2新人礼券")
    private Integer type;

    @ApiModelProperty("使用类型，1全场，2指定分类，3指定商品")
    private Integer useType;

    @ApiModelProperty("生效时间类型，1天数，2时间段")
    private Integer timeType;

    @ApiModelProperty("有效期限，自领取后几天内有效，0当天")
    private Integer days;

    @ApiModelProperty("有效期限，开始时间")
    private Date startTime;

    @ApiModelProperty("有效期限，结束时间")
    private Date endTime;

    @ApiModelProperty("删除标识，0未删除，1删除")
    private Integer deleted;

    private static final long serialVersionUID = 1L;

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
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

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
        sb.append(", couponId=").append(couponId);
        sb.append(", createTime=").append(createTime);
        sb.append(", createId=").append(createId);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateId=").append(updateId);
        sb.append(", name=").append(name);
        sb.append(", desc=").append(desc);
        sb.append(", minPrice=").append(minPrice);
        sb.append(", discount=").append(discount);
        sb.append(", limit=").append(limit);
        sb.append(", total=").append(total);
        sb.append(", status=").append(status);
        sb.append(", type=").append(type);
        sb.append(", useType=").append(useType);
        sb.append(", timeType=").append(timeType);
        sb.append(", days=").append(days);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", deleted=").append(deleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}