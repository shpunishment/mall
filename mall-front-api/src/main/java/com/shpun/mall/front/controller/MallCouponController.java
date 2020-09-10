package com.shpun.mall.front.controller;

import com.github.pagehelper.PageInfo;
import com.shpun.mall.common.model.vo.MallCouponVo;
import com.shpun.mall.common.model.vo.MallProductVo;
import com.shpun.mall.common.service.MallCouponService;
import com.shpun.mall.common.service.MallUserCouponService;
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
 * @Date: 2020/9/2 17:35
 */
@Api(tags = "优惠券控制器")
@RequestMapping("/api/coupon")
@RestController
@Validated
public class MallCouponController {

    @Autowired
    private MallCouponService couponService;

    @Autowired
    private MallUserCouponService userCouponService;

    @ApiOperation("分页获取可领取优惠券")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "offset", value = "偏移量", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "数量", dataType = "Integer")
    })
    @GetMapping("/page")
    public PageInfo<MallCouponVo> page(@RequestParam(value = "offset", defaultValue = "0") @Min(0) @Max(2147483647) Integer offset,
                                       @RequestParam(value = "limit", defaultValue = "10") @Min(1) @Max(2147483647) Integer limit) {

        PageInfo<MallCouponVo> couponVoPageInfo = couponService.getVoPage(offset, limit);

        if (CollectionUtils.isNotEmpty(couponVoPageInfo.getList()) && SecurityUserUtils.getUserId() != null) {
            List<Integer> couponIdList = couponVoPageInfo.getList().stream().map(MallCouponVo::getCouponId).collect(Collectors.toList());
            List<Integer> receivedList = userCouponService.getReceivedCouponId(SecurityUserUtils.getUserId(), couponIdList);

            for (MallCouponVo couponVo : couponVoPageInfo.getList()) {
                Integer couponId = couponVo.getCouponId();
                if (receivedList.contains(couponId)) {
                    couponVo.setReceived(true);
                } else {
                    couponVo.setReceived(false);
                }

                // 数量标识
                couponVo.setTotal(null);
                couponVo.setHasTotal(true);
            }
        }
        return couponVoPageInfo;
    }

}
