package com.shpun.mall.common.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/23 18:12
 */
@ApiModel
public class MallCartVo implements Serializable {

    @ApiModelProperty("购物车id")
    private Integer cartId;

    @ApiModelProperty("商品id")
    private Integer productId;

    @ApiModelProperty("数量")
    private Integer quantity;

    @ApiModelProperty("商品信息")
    private MallProductVo productVo;

    private static final long serialVersionUID = 1L;

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
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

    public MallProductVo getProductVo() {
        return productVo;
    }

    public void setProductVo(MallProductVo productVo) {
        this.productVo = productVo;
    }
}
