package com.shpun.mall.front.controller;

import com.github.pagehelper.PageInfo;
import com.shpun.mall.common.enums.MallCouponUseTypeEnums;
import com.shpun.mall.common.exception.MallError;
import com.shpun.mall.common.exception.MallException;
import com.shpun.mall.common.model.MallCoupon;
import com.shpun.mall.common.model.MallUserFootprint;
import com.shpun.mall.common.model.vo.MallCouponVo;
import com.shpun.mall.common.model.vo.MallFlashVo;
import com.shpun.mall.common.model.vo.MallProductVo;
import com.shpun.mall.common.service.*;
import com.shpun.mall.front.security.SecurityUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/23 16:02
 */
@Api(tags = "商品控制器")
@RequestMapping("/api/product")
@RestController
@Validated
public class MallProductController {

    @Autowired
    private MallProductService productService;

    @Autowired
    private MallCouponService couponService;

    @Autowired
    private MallUserFootprintService userFootprintService;

    @Autowired
    private MallFlashService flashService;

    @Autowired
    private MallFlashItemService flashItemService;

    @Autowired
    private MallUserCouponService userCouponService;

    @ApiOperation("根据商品二级分类id分页获取商品")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "classifyId", value = "商品二级分类id", dataType = "Integer"),
            @ApiImplicitParam(name = "inStock", value = "有货过滤，0不过滤，1过滤", dataType = "Integer"),
            @ApiImplicitParam(name = "priceSort", value = "价格排序，1顺序，2倒序", dataType = "Integer"),
            @ApiImplicitParam(name = "offset", value = "偏移量", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "数量", dataType = "Integer")
    })
    @GetMapping("/getByClassifyId/{classifyId}")
    public PageInfo<MallProductVo> getByClassifyId(@PathVariable("classifyId") @Min(1) @Max(2147483647) Integer classifyId,
                                                   @RequestParam(value = "inStock", defaultValue = "0") @Min(0) @Max(1) Integer inStock,
                                                   @RequestParam(value = "priceSort", defaultValue = "1") @Min(1) @Max(2) Integer priceSort,
                                                   @RequestParam(value = "offset", defaultValue = "0") @Min(0) @Max(2147483647) Integer offset,
                                                   @RequestParam(value = "limit", defaultValue = "10") @Min(1) @Max(2147483647) Integer limit) {

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
    public MallProductVo detail(@PathVariable("productId") @Min(1) @Max(2147483647) Integer productId) {
        MallProductVo productVo = productService.getDetailVo(productId);
        if (productVo == null) {
            throw new MallException(MallError.MallErrorEnum.PRODUCT_NOT_FOUND.format(productId));
        }

        // 添加用户足迹
        if (SecurityUserUtils.getUserId() != null) {
            MallUserFootprint userFootprint = new MallUserFootprint();
            userFootprint.setUserId(SecurityUserUtils.getUserId());
            userFootprint.setProductId(productId);
            userFootprintService.insertSelective(userFootprint);
        }

        // 检查商品是否在限时抢购，在用户购物车中，是否收藏
        productService.additionalVo(productVo, SecurityUserUtils.getUserId(), true, true);

        // 非限时抢购商品，添加该商品可用优惠券
        if (!productVo.getFlashing()) {
            List<MallCouponVo> couponVoList = couponService.getVoListByProductId(productId);

            // 添加用户领取标识，库存标识
            userCouponService.additionalVoList(couponVoList, SecurityUserUtils.getUserId());
            productVo.setCouponVoList(couponVoList);
        }

        return productVo;
    }

    @ApiOperation("根据优惠券id分页获取商品")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "couponId", value = "优惠券id", dataType = "Integer"),
            @ApiImplicitParam(name = "inStock", value = "有货过滤，0不过滤，1过滤", dataType = "Integer"),
            @ApiImplicitParam(name = "priceSort", value = "价格排序，1顺序，2倒序", dataType = "Integer"),
            @ApiImplicitParam(name = "offset", value = "偏移量", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "数量", dataType = "Integer")
    })
    @GetMapping("/getByCouponId/{couponId}")
    public PageInfo<MallProductVo> getProductVoByCouponId(@PathVariable("couponId") @Min(1) @Max(2147483647) Integer couponId,
                                                          @RequestParam(value = "inStock", defaultValue = "0") @Min(0) @Max(1) Integer inStock,
                                                          @RequestParam(value = "priceSort", defaultValue = "1") @Min(1) @Max(2) Integer priceSort,
                                                          @RequestParam(value = "offset", defaultValue = "0") @Min(0) @Max(2147483647) Integer offset,
                                                          @RequestParam(value = "limit", defaultValue = "10") @Min(1) @Max(2147483647) Integer limit) {

        // 判断优惠券id是否可用
        MallCoupon coupon = couponService.getAvailable(couponId);
        if (coupon == null) {
            throw new MallException(MallError.MallErrorEnum.COUPON_ERROR);
        }

        PageInfo<MallProductVo> productVoPageInfo = null;
        if (MallCouponUseTypeEnums.ALL.getValue().equals(coupon.getUseType())) {
            // 获取今日限时抢购
            List<MallFlashVo> flashVoList = flashService.getTodayAvailableVoList();
            if (CollectionUtils.isNotEmpty(flashVoList)) {
                List<Integer> flashIdList = flashVoList.stream().map(MallFlashVo::getFlashId).collect(Collectors.toList());
                // 获取今日限时抢购商品
                List<Integer> flashProductIdList = flashItemService.getProductIdByFlashIdList(flashIdList);
                // 搜索商品排除限时抢购商品
                productVoPageInfo = productService.getVoPageByFilterNotProductIdList(flashProductIdList, inStock, priceSort, offset, limit);
            } else {
                productVoPageInfo = productService.getVoPageByFilter("", inStock, priceSort, offset, limit);
            }
        } else if (MallCouponUseTypeEnums.CLASSIFY.getValue().equals(coupon.getUseType())) {
            List<Integer> classifyIdList = couponService.getClassifyIdList(couponId);
            productVoPageInfo = productService.getVoPageByFilterClassifyIdList(classifyIdList, inStock, priceSort, offset, limit);

        } else if (MallCouponUseTypeEnums.PRODUCT.getValue().equals(coupon.getUseType())) {
            List<Integer> productIdList = couponService.getProductIdList(couponId);
            productVoPageInfo = productService.getVoPageByFilterProductIdList(productIdList, inStock, priceSort, offset, limit);
        }

        // 检查商品是否在用户购物车中
        productService.additionalVoList(productVoPageInfo.getList(), SecurityUserUtils.getUserId(), false);
        return productVoPageInfo;
    }

}
