package com.shpun.mall.front.controller;

import com.github.pagehelper.PageInfo;
import com.shpun.mall.common.enums.MallCouponUseTypeEnums;
import com.shpun.mall.common.exception.MallException;
import com.shpun.mall.common.model.MallCoupon;
import com.shpun.mall.common.model.vo.MallProductVo;
import com.shpun.mall.common.service.MallCouponService;
import com.shpun.mall.common.service.MallProductService;
import com.shpun.mall.front.security.SecurityUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/23 16:02
 */
@Api(tags = "商品控制器")
@RequestMapping("/api/product")
@RestController
public class MallProductController {

    @Autowired
    private MallProductService productService;

    @Autowired
    private MallCouponService couponService;

    @ApiOperation("根据商品二级分类id分页获取商品")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "classifyId", value = "商品二级分类id", dataType = "Integer"),
            @ApiImplicitParam(name = "inStock", value = "有货过滤", dataType = "Integer"),
            @ApiImplicitParam(name = "priceSort", value = "价格排序，1顺序，2倒序", dataType = "Integer"),
            @ApiImplicitParam(name = "offset", value = "偏移量", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "数量", dataType = "Integer")
    })
    @GetMapping("/getByClassifyId")
    public PageInfo<MallProductVo> getByClassifyId(@RequestParam("classifyId") Integer classifyId,
                                                   @RequestParam(value = "inStock", defaultValue = "0") Integer inStock,
                                                   @RequestParam(value = "priceSort", defaultValue = "1") Integer priceSort,
                                                   @RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                                   @RequestParam(value = "limit", defaultValue = "10") Integer limit) {

        PageInfo<MallProductVo> productVoPageInfo = productService.getVoPageByFilter(classifyId, inStock, priceSort, offset, limit);

        // 检查商品是否在限时抢购，在用户购物车中
        productService.additionalVoList(productVoPageInfo.getList(), SecurityUserUtils.getUserId(), true);
        return productVoPageInfo;
    }

    @ApiOperation("获取商品详情")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "productId", value = "商品id", dataType = "Integer")
    })
    @GetMapping("/detail/{productId}")
    public MallProductVo detail(@PathVariable("productId") Integer productId) {
        MallProductVo productVo = productService.getDetailVo(productId);
        if (productVo == null) {
            throw new MallException("商品不存在！");
        }

        // 检查商品是否在限时抢购，在用户购物车中，是否收藏
        productService.additionalVo(productVo, SecurityUserUtils.getUserId(), true, true);
        return productVo;
    }

    @ApiOperation("根据优惠券id分页获取商品")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "couponId", value = "优惠券id", dataType = "Integer"),
            @ApiImplicitParam(name = "offset", value = "偏移量", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "数量", dataType = "Integer")
    })
    @GetMapping("/getByCouponId/{couponId}")
    public PageInfo<MallProductVo> getProductVoByCouponId(@PathVariable("couponId") Integer couponId,
                                                          @RequestParam(value = "inStock", defaultValue = "0") Integer inStock,
                                                          @RequestParam(value = "priceSort", defaultValue = "1") Integer priceSort,
                                                          @RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                                          @RequestParam(value = "limit", defaultValue = "10") Integer limit) {

        // 判断优惠券id是否可用
        MallCoupon coupon = couponService.getAvailable(couponId);
        if (coupon == null) {
            throw new MallException("优惠券异常！");
        }

        PageInfo<MallProductVo> productVoPageInfo = null;
        if (MallCouponUseTypeEnums.CLASSIFY.getValue().equals(coupon.getUseType())) {
            List<Integer> classifyIdList = couponService.getClassifyIdList(couponId);
            productVoPageInfo = productService.getVoPageByFilterClassifyId(classifyIdList, inStock, priceSort, offset, limit);

        } else if (MallCouponUseTypeEnums.PRODUCT.getValue().equals(coupon.getUseType())) {
            List<Integer> productIdList = couponService.getProductIdList(couponId);
            productVoPageInfo = productService.getVoPageByFilterProductId(productIdList, inStock, priceSort, offset, limit);
        }

        // 检查商品是否在用户购物车中
        productService.additionalVoList(productVoPageInfo.getList(), SecurityUserUtils.getUserId(), false);
        return productVoPageInfo;
    }

}
