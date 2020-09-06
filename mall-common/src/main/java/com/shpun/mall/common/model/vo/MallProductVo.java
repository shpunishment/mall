package com.shpun.mall.common.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/23 18:15
 */
@ApiModel
public class MallProductVo implements Serializable {

    @ApiModelProperty("商品id")
    private Integer productId;

    @ApiModelProperty("商品名称")
    private String productName;

    @ApiModelProperty("商品描述")
    private String desc;

    @ApiModelProperty("品牌id")
    private Integer brandId;

    @ApiModelProperty("品牌信息")
    private MallBrandVo brandVo;

    @ApiModelProperty("商品图片，为文件id")
    private Integer pic;

    @ApiModelProperty("商品原价")
    private BigDecimal originalPrice;

    @ApiModelProperty("商品现价")
    private BigDecimal currentPrice;

    @ApiModelProperty("打折数")
    private String discountStr;

    @ApiModelProperty("库存")
    private Integer stock;

    @ApiModelProperty("库存标识")
    private Boolean hasStock;

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

    @ApiModelProperty("规格")
    private String format;

    @ApiModelProperty("存储条件")
    private String storage;

    @ApiModelProperty("产地")
    private String origin;

    @ApiModelProperty("商品详情，为文件id")
    private Integer detail;

    @ApiModelProperty("购物车信息")
    private MallCartVo cartVo;

    @ApiModelProperty("是否正在抢购")
    private Boolean flashing;

    @ApiModelProperty("限时抢购商品剩余库存百分比")
    private String remainStockPercent;

    @ApiModelProperty("限时抢购结束时间")
    private Date flashEndTime;

    @ApiModelProperty("收藏id")
    private Integer favoriteId;

    @ApiModelProperty("足迹id")
    private Integer footprintId;

    private static final long serialVersionUID = 1L;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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

    public MallBrandVo getBrandVo() {
        return brandVo;
    }

    public void setBrandVo(MallBrandVo brandVo) {
        this.brandVo = brandVo;
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

    public String getDiscountStr() {
        return discountStr;
    }

    public void setDiscountStr(String discountStr) {
        this.discountStr = discountStr;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Boolean getHasStock() {
        return hasStock;
    }

    public void setHasStock(Boolean hasStock) {
        this.hasStock = hasStock;
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

    public MallCartVo getCartVo() {
        return cartVo;
    }

    public void setCartVo(MallCartVo cartVo) {
        this.cartVo = cartVo;
    }

    public Boolean getFlashing() {
        return flashing;
    }

    public void setFlashing(Boolean flashing) {
        this.flashing = flashing;
    }

    public String getRemainStockPercent() {
        return remainStockPercent;
    }

    public void setRemainStockPercent(String remainStockPercent) {
        this.remainStockPercent = remainStockPercent;
    }


    public Date getFlashEndTime() {
        return flashEndTime;
    }

    public void setFlashEndTime(Date flashEndTime) {
        this.flashEndTime = flashEndTime;
    }

    public Integer getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(Integer favoriteId) {
        this.favoriteId = favoriteId;
    }

    public Integer getFootprintId() {
        return footprintId;
    }

    public void setFootprintId(Integer footprintId) {
        this.footprintId = footprintId;
    }
}
