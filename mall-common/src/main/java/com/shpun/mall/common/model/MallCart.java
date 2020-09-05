package com.shpun.mall.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 购物车
 * @Author: sun
 * @Date: 2020/8/22 10:34
 */
@ApiModel
public class MallCart implements Serializable {

    @NotNull(groups = Update.class)
    @Min(value = 1, groups = Update.class)
    @Max(value = 2147483647, groups = Update.class)
    @ApiModelProperty("购物车id")
    private Integer cartId;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("用户id")
    private Integer userId;

    @NotNull(groups = { Add.class, Update.class })
    @Min(value = 1, groups = { Add.class, Update.class })
    @Max(value = 2147483647, groups = { Add.class, Update.class })
    @ApiModelProperty("商品id")
    private Integer productId;

    @NotNull(groups = { Add.class, Update.class })
    @Min(value = 1, groups = { Add.class, Update.class })
    @Max(value = 2147483647, groups = { Add.class, Update.class })
    @ApiModelProperty("数量")
    private Integer quantity;

    @ApiModelProperty("删除标识，0未删除，1删除")
    private Integer deleted;

    private static final long serialVersionUID = 1L;

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
        sb.append(", cartId=").append(cartId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", userId=").append(userId);
        sb.append(", productId=").append(productId);
        sb.append(", quantity=").append(quantity);
        sb.append(", deleted=").append(deleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 购物车新增校验分组
     */
    public interface Add {
    }

    /**
     * 购物车更新校验分组
     */
    public interface Update {
    }

}