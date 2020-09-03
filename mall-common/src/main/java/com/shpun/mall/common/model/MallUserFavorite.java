package com.shpun.mall.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 用户收藏
 * @Author: sun
 * @Date: 2020/8/22 10:34
 */
@ApiModel
public class MallUserFavorite implements Serializable {

    @ApiModelProperty("收藏id")
    private Integer favoriteId;

    @ApiModelProperty("收藏时间")
    private Date favoriteTime;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("商品id")
    private Integer productId;

    @ApiModelProperty("删除标识，0未删除，1删除")
    private Integer deleted;

    private static final long serialVersionUID = 1L;

    public Integer getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(Integer favoriteId) {
        this.favoriteId = favoriteId;
    }

    public Date getFavoriteTime() {
        return favoriteTime;
    }

    public void setFavoriteTime(Date favoriteTime) {
        this.favoriteTime = favoriteTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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
        sb.append(", favoriteId=").append(favoriteId);
        sb.append(", favoriteTime=").append(favoriteTime);
        sb.append(", userId=").append(userId);
        sb.append(", productId=").append(productId);
        sb.append(", deleted=").append(deleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}