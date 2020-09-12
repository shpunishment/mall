package com.shpun.mall.common.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/23 18:12
 */
@ApiModel
public class MallFlashVo implements Serializable {

    @ApiModelProperty("限时抢购id")
    private Integer flashId;

    @ApiModelProperty("限时抢购开始时间，如2020-07-27 09:00:00")
    private Date startTime;

    @ApiModelProperty("年")
    private Integer year;

    @ApiModelProperty("月")
    private Integer month;

    @ApiModelProperty("日")
    private Integer day;

    @ApiModelProperty("时")
    private Integer hour;

    @ApiModelProperty("分")
    private Integer minute;

    @ApiModelProperty("结束时间")
    private Date endTime;

    @ApiModelProperty("今日活动标识")
    private Boolean today;

    private static final long serialVersionUID = 1L;

    public Integer getFlashId() {
        return flashId;
    }

    public void setFlashId(Integer flashId) {
        this.flashId = flashId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Boolean getToday() {
        return today;
    }

    public void setToday(Boolean today) {
        this.today = today;
    }
}
