package com.shpun.mall.front.controller;

import com.github.pagehelper.PageInfo;
import com.shpun.mall.common.enums.MallUserCouponGetTypeEnums;
import com.shpun.mall.common.enums.MallUserCouponStatusEnums;
import com.shpun.mall.common.model.MallOrder;
import com.shpun.mall.common.model.MallUserCoupon;
import com.shpun.mall.common.model.vo.MallUserCouponVo;
import com.shpun.mall.common.service.MallOrderService;
import com.shpun.mall.common.service.MallUserCouponService;
import com.shpun.mall.front.security.SecurityUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/9/3 10:36
 */
@Api(tags = "用户优惠券控制器")
@RequestMapping("/api/user/coupon")
@RestController
public class MallUserCouponController {

    @Autowired
    private MallUserCouponService userCouponService;

    @Autowired
    private MallOrderService orderService;

    @ApiOperation("领取优惠券")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "couponId", value = "优惠券id", dataType = "Integer")
    })
    @GetMapping("/{couponId}")
    public void get(@PathVariable("couponId") Integer couponId) {
        MallUserCoupon userCoupon = new MallUserCoupon();
        userCoupon.setUserId(SecurityUserUtils.getUserId());
        userCoupon.setCouponId(couponId);
        userCoupon.setGetType(MallUserCouponGetTypeEnums.INITIATIVE.getValue());
        userCouponService.insertSelective(userCoupon);
    }

    @ApiOperation("分页获取优惠券")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "status", value = "使用状态", dataType = "Integer"),
            @ApiImplicitParam(name = "offset", value = "偏移量", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "数量", dataType = "Integer")
    })
    @GetMapping("/page")
    public PageInfo<MallUserCouponVo> page(@RequestParam(value = "status", defaultValue = "1") Integer status,
                                           @RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                           @RequestParam(value = "limit", defaultValue = "10") Integer limit) {

        PageInfo<MallUserCouponVo> userCouponVoPageInfo = userCouponService.getVoPageByFilter(SecurityUserUtils.getUserId(), status, offset, limit);
        return userCouponVoPageInfo;
    }

    @ApiOperation("检查优惠券是否可用")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "status", value = "使用状态", dataType = "Integer"),
            @ApiImplicitParam(name = "offset", value = "偏移量", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "数量", dataType = "Integer")
    })
    @PostMapping("/check")
    public Map<String, List<MallUserCouponVo>> checkCouponAvailable(@RequestParam("cartIdList") List<Integer> cartIdList) {

        List<MallUserCouponVo> userCouponVoList = userCouponService.getVoListByFilter(SecurityUserUtils.getUserId(), MallUserCouponStatusEnums.UNUSED.getValue());

        Map<String, List<MallUserCouponVo>> resultMap = null;
        if (CollectionUtils.isNotEmpty(userCouponVoList)) {
            resultMap = new HashMap<>(2);
            resultMap.put("can", new ArrayList<>(userCouponVoList.size()));
            resultMap.put("cannot", new ArrayList<>(userCouponVoList.size()));

            for (MallUserCouponVo userCouponVo : userCouponVoList) {
                MallOrder order = orderService.calculatePrice(cartIdList, userCouponVo.getCouponId());
                if (order.getCouponId() != null) {
                    List<MallUserCouponVo> canUseList = resultMap.get("can");
                    canUseList.add(userCouponVo);
                } else {
                    List<MallUserCouponVo> cannotUseList = resultMap.get("cannot");
                    cannotUseList.add(userCouponVo);
                }
            }
        }

        return resultMap;
    }

}
