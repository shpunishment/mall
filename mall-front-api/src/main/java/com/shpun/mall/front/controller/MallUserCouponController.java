package com.shpun.mall.front.controller;

import com.github.pagehelper.PageInfo;
import com.shpun.mall.common.common.Const;
import com.shpun.mall.common.enums.MallCouponTypeEnums;
import com.shpun.mall.common.enums.MallUserCouponGetTypeEnums;
import com.shpun.mall.common.exception.MallError;
import com.shpun.mall.common.exception.MallException;
import com.shpun.mall.common.model.MallUserCoupon;
import com.shpun.mall.common.model.vo.MallCouponVo;
import com.shpun.mall.common.service.MallCouponService;
import com.shpun.mall.common.service.MallUserCouponService;
import com.shpun.mall.front.security.SecurityUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/9/3 10:36
 */
@Api(tags = "用户优惠券控制器")
@RequestMapping("/api/user/coupon")
@RestController
@Validated
public class MallUserCouponController {

    @Autowired
    private MallUserCouponService userCouponService;

    @Autowired
    private MallCouponService couponService;

    @ApiOperation("领取优惠券")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "couponId", value = "优惠券id", dataType = "Integer")
    })
    @GetMapping("/{couponId}")
    public void get(@PathVariable("couponId") @Min(1) @Max(2147483647) Integer couponId) {
        MallCouponVo couponVo = couponService.getAvailable(couponId);
        // 用户只能主动领取 通用券类型的优惠券
        if (MallCouponTypeEnums.UNIVERSAL.getValue().equals(couponVo.getType())) {
            MallUserCoupon userCoupon = new MallUserCoupon();
            userCoupon.setUserId(SecurityUserUtils.getUserId());
            userCoupon.setCouponId(couponId);
            userCoupon.setGetType(MallUserCouponGetTypeEnums.INITIATIVE.getValue());
            userCouponService.insertSelective(userCoupon);

            // 删除优惠券缓存
            couponService.deleteCache();
            // 删除用户优惠券缓存
            userCouponService.deleteCache(SecurityUserUtils.getUserId());
        } else {
            throw new MallException(MallError.MallErrorEnum.INTERNAL_SYSTEM_ERROR);
        }
    }

    @ApiOperation("分页获取用户已领取的优惠券")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "status", value = "使用状态", dataType = "Integer"),
            @ApiImplicitParam(name = "offset", value = "偏移量", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "数量", dataType = "Integer")
    })
    @GetMapping("/page")
    public PageInfo<MallCouponVo> page(@RequestParam(value = "status", defaultValue = "1") @Min(1) @Max(3) Integer status,
                                       @RequestParam(value = "offset", defaultValue = "0") @Min(0) @Max(2147483647) Integer offset,
                                       @RequestParam(value = "limit", defaultValue = "10") @Min(1) @Max(2147483647) Integer limit) {

        PageInfo<MallCouponVo> couponVoPageInfo = userCouponService.getCouponVoPageByFilter(SecurityUserUtils.getUserId(), status, offset, limit);

        // 添加用户领取标识，库存标识
        couponService.additionalVoList(couponVoPageInfo.getList(), SecurityUserUtils.getUserId());
        return couponVoPageInfo;
    }

    @ApiOperation("今日是否能使用优惠券")
    @GetMapping("/can")
    public Boolean canUseCoupon() {
        if (userCouponService.getTodayUseCount(SecurityUserUtils.getUserId()) < Const.TODAY_USE_COUPON_COUNT) {
            Integer couponCount = userCouponService.getAvailableCount(SecurityUserUtils.getUserId());
            return couponCount > 0;
        } else {
            return false;
        }
    }

}
