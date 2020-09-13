package com.shpun.mall.common.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/9/7 14:08
 */
@ApiModel
public class MallActivityClassifyVo implements Serializable {

    @ApiModelProperty("活动分类id")
    private Integer classifyId;

    @ApiModelProperty("分类名")
    private String classifyName;

    @ApiModelProperty("活动id")
    private Integer activityId;

    @ApiModelProperty("活动名称")
    private String activityName;

    @ApiModelProperty("商品列表")
    private List<MallProductVo> productVoList;

    private static final long serialVersionUID = 1L;

    public Integer getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Integer classifyId) {
        this.classifyId = classifyId;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public List<MallProductVo> getProductVoList() {
        return productVoList;
    }

    public void setProductVoList(List<MallProductVo> productVoList) {
        this.productVoList = productVoList;
    }
}
