package com.shpun.mall.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: 限时抢购商品
 * @Author: sun
 * @Date: 2020/8/22 10:34
 */
@ApiModel
public class MallFlashItem implements Serializable {

    @ApiModelProperty("限时抢购商品id")
    private Integer flashItemId;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("创建管理员id")
    private Integer createId;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("更新管理员id")
    private Integer updateId;

    @ApiModelProperty("限时抢购id")
    private Integer flashId;

    @ApiModelProperty("商品id")
    private Integer productId;

    @ApiModelProperty("库存")
    private Integer stock;

    @ApiModelProperty("销量")
    private Integer sales;

    @ApiModelProperty("价格")
    private BigDecimal price;

    @ApiModelProperty("限购数量，0不限制")
    private Integer limit;

    @ApiModelProperty("删除标识，0未删除，1删除")
    private Integer deleted;

    private static final long serialVersionUID = 1L;

    public Integer getFlashItemId() {
        return flashItemId;
    }

    public void setFlashItemId(Integer flashItemId) {
        this.flashItemId = flashItemId;
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

    public Integer getFlashId() {
        return flashId;
    }

    public void setFlashId(Integer flashId) {
        this.flashId = flashId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
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
        sb.append(", flashItemId=").append(flashItemId);
        sb.append(", createTime=").append(createTime);
        sb.append(", createId=").append(createId);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateId=").append(updateId);
        sb.append(", flashId=").append(flashId);
        sb.append(", productId=").append(productId);
        sb.append(", stock=").append(stock);
        sb.append(", sales=").append(sales);
        sb.append(", price=").append(price);
        sb.append(", limit=").append(limit);
        sb.append(", deleted=").append(deleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}