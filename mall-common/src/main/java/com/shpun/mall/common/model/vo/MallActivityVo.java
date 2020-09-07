package com.shpun.mall.common.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/9/7 14:08
 */
@ApiModel
public class MallActivityVo implements Serializable {

    @ApiModelProperty("活动id")
    private Integer activityId;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("活动图，为文件id")
    private Integer pic;

    private static final long serialVersionUID = 1L;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPic() {
        return pic;
    }

    public void setPic(Integer pic) {
        this.pic = pic;
    }
}
