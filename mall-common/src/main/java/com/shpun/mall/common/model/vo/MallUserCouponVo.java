package com.shpun.mall.common.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/9/3 10:57
 */
@ApiModel
public class MallUserCouponVo implements Serializable {

    @ApiModelProperty("领取时间")
    private Date createTime;

    @ApiModelProperty("优惠券id")
    private Integer couponId;

    @ApiModelProperty("优惠券信息")
    private MallCouponVo couponVo;

    @ApiModelProperty("使用状态，1未使用，2已使用，3已过期")
    private Integer status;

    @ApiModelProperty("有效期限，开始时间")
    private Date startTime;

    @ApiModelProperty("有效期限，结束时间")
    private Date endTime;

    @ApiModelProperty("订单信息")
    private MallOrderVo orderVo;

    private static final long serialVersionUID = 1L;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public MallCouponVo getCouponVo() {
        return couponVo;
    }

    public void setCouponVo(MallCouponVo couponVo) {
        this.couponVo = couponVo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public MallOrderVo getOrderVo() {
        return orderVo;
    }

    public void setOrderVo(MallOrderVo orderVo) {
        this.orderVo = orderVo;
    }
}
