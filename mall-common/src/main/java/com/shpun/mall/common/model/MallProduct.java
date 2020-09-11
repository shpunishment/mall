package com.shpun.mall.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: 商品
 * @Author: sun
 * @Date: 2020/8/22 10:34
 */
@ApiModel
public class MallProduct implements Serializable {

    @ApiModelProperty("商品id")
    private Integer productId;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("创建管理员id")
    private Integer createId;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("更新管理员id")
    private Integer updateId;

    @ApiModelProperty("商品名称")
    private String productName;

    @ApiModelProperty("商品描述")
    private String desc;

    @ApiModelProperty("品牌id")
    private Integer brandId;

    @ApiModelProperty("商品图片，为文件id")
    private Integer pic;

    @ApiModelProperty("商品原价")
    private BigDecimal originalPrice;

    @ApiModelProperty("商品现价")
    private BigDecimal currentPrice;

    @ApiModelProperty("库存")
    private Integer stock;

    @ApiModelProperty("限购数量，默认10")
    private Integer limit;

    @ApiModelProperty("销量")
    private Integer sales;

    @ApiModelProperty("新品状态，0否，1是")
    private Integer newStatus;

    @ApiModelProperty("上架状态，0否，1是")
    private Integer publishStatus;

    @ApiModelProperty("标签")
    private String label;

    @ApiModelProperty("标签颜色")
    private String labelColor;

    @ApiModelProperty("规格")
    private String format;

    @ApiModelProperty("存储条件")
    private String storage;

    @ApiModelProperty("产地")
    private String origin;

    @ApiModelProperty("商品详情，为文件id")
    private Integer detail;

    @ApiModelProperty("删除标识，0未删除，1删除")
    private Integer deleted;

    @ApiModelProperty("是否正在抢购")
    private Boolean flashing;

    private static final long serialVersionUID = 1L;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getPic() {
        return pic;
    }

    public void setPic(Integer pic) {
        this.pic = pic;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(Integer newStatus) {
        this.newStatus = newStatus;
    }

    public Integer getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(Integer publishStatus) {
        this.publishStatus = publishStatus;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabelColor() {
        return labelColor;
    }

    public void setLabelColor(String labelColor) {
        this.labelColor = labelColor;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Integer getDetail() {
        return detail;
    }

    public void setDetail(Integer detail) {
        this.detail = detail;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Boolean getFlashing() {
        return flashing;
    }

    public void setFlashing(Boolean flashing) {
        this.flashing = flashing;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", productId=").append(productId);
        sb.append(", createTime=").append(createTime);
        sb.append(", createId=").append(createId);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateId=").append(updateId);
        sb.append(", productName=").append(productName);
        sb.append(", desc=").append(desc);
        sb.append(", brandId=").append(brandId);
        sb.append(", pic=").append(pic);
        sb.append(", originalPrice=").append(originalPrice);
        sb.append(", currentPrice=").append(currentPrice);
        sb.append(", stock=").append(stock);
        sb.append(", limit=").append(limit);
        sb.append(", sales=").append(sales);
        sb.append(", newStatus=").append(newStatus);
        sb.append(", publishStatus=").append(publishStatus);
        sb.append(", label=").append(label);
        sb.append(", labelColor=").append(labelColor);
        sb.append(", format=").append(format);
        sb.append(", storage=").append(storage);
        sb.append(", origin=").append(origin);
        sb.append(", detail=").append(detail);
        sb.append(", deleted=").append(deleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}