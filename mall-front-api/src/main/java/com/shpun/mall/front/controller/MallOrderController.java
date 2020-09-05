package com.shpun.mall.front.controller;

import com.github.pagehelper.PageInfo;
import com.shpun.mall.common.common.Const;
import com.shpun.mall.common.enums.MallUserCouponStatusEnums;
import com.shpun.mall.common.enums.MallUserSearchHistoryTypeEnums;
import com.shpun.mall.common.exception.MallError;
import com.shpun.mall.common.exception.MallException;
import com.shpun.mall.common.model.MallOrder;
import com.shpun.mall.common.model.vo.MallOrderItemVo;
import com.shpun.mall.common.model.vo.MallOrderVo;
import com.shpun.mall.common.model.vo.MallUserCouponVo;
import com.shpun.mall.common.service.*;
import com.shpun.mall.front.security.SecurityUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.*;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/23 16:02
 */
@Api(tags = "订单控制器")
@RequestMapping("/api/order")
@RestController
public class MallOrderController {

    @Autowired
    private MallOrderService orderService;

    @Autowired
    private MallOrderItemService orderItemService;

    @Autowired
    private MallUserSearchHistoryService userSearchHistoryService;

    @Autowired
    private MallCartService cartService;

    @Autowired
    private MallProductService productService;

    @Autowired
    private MallFlashItemService flashItemService;

    @Autowired
    private MallUserCouponService userCouponService;

    @ApiOperation("计算价格")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "cartIdList", value = "购物车idList", dataType = "List<Integer>")
    })
    @PostMapping("/calculate")
    public MallOrder calculatePrice(@RequestParam("cartIdList") List<Integer> cartIdList) {
        return orderService.calculatePrice(cartIdList);
    }

    @ApiOperation("根据用户优惠券计算价格")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "cartIdList", value = "购物车idList", dataType = "List<Integer>")
    })
    @PostMapping("/calculateWithCoupon")
    public Map<String, List<MallUserCouponVo>> calculateWithCoupon(@RequestParam("cartIdList") List<Integer> cartIdList) {

        // 获取用户所有未使用的优惠券
        List<MallUserCouponVo> userCouponVoList = userCouponService.getVoListByFilter(SecurityUserUtils.getUserId(), MallUserCouponStatusEnums.UNUSED.getValue());

        Map<String, List<MallUserCouponVo>> resultMap = null;
        if (CollectionUtils.isNotEmpty(userCouponVoList)) {
            resultMap = new HashMap<>(2);
            resultMap.put("can", new ArrayList<>(userCouponVoList.size()));
            resultMap.put("cannot", new ArrayList<>(userCouponVoList.size()));

            // 今日是否可用优惠券
            boolean canTodayUse = userCouponService.getTodayUseCount(SecurityUserUtils.getUserId()) < Const.TODAY_USE_COUPON_COUNT;
            for (MallUserCouponVo userCouponVo : userCouponVoList) {
                if (canTodayUse) {
                    // 判断优惠券使用时间，是否可用
                    Date startTime = userCouponVo.getStartTime();
                    Date endTime = userCouponVo.getEndTime();
                    Date now = new Date();
                    if (startTime.compareTo(now) <= 0 && endTime.compareTo(now) >= 0) {
                        MallOrder order = orderService.calculatePrice(cartIdList, userCouponVo.getCouponId());
                        if (order.getCouponId() != null) {
                            MallOrderVo orderVo = new MallOrderVo();
                            BeanUtils.copyProperties(order, orderVo);
                            userCouponVo.setOrderVo(orderVo);
                            List<MallUserCouponVo> canUseList = resultMap.get("can");
                            canUseList.add(userCouponVo);
                        } else {
                            List<MallUserCouponVo> cannotUseList = resultMap.get("cannot");
                            cannotUseList.add(userCouponVo);
                        }
                    } else {
                        List<MallUserCouponVo> cannotUseList = resultMap.get("cannot");
                        cannotUseList.add(userCouponVo);
                    }
                } else {
                    List<MallUserCouponVo> cannotUseList = resultMap.get("cannot");
                    cannotUseList.add(userCouponVo);
                }
            }
        }
        return resultMap;
    }

    @ApiOperation("生成订单")
    @PostMapping("/generate")
    public void generateOrder(@RequestBody MallOrderVo orderVo) {
        MallOrder order = new MallOrder();
        BeanUtils.copyProperties(orderVo, order);
        order.setUserId(SecurityUserUtils.getUserId());
        orderService.generateOrder(order, orderVo.getCartIdList());

        // 删除购物车缓存
        cartService.deleteCache(SecurityUserUtils.getUserId());
        // 删除商品缓存
        productService.deleteCache();
        // 删除限时抢购缓存
        flashItemService.deleteCache();
        // 删除订单缓存
        orderService.deleteCache(SecurityUserUtils.getUserId());
        // 删除用户优惠券缓存
        userCouponService.deleteCache(SecurityUserUtils.getUserId());
    }

    @ApiOperation("分页获取订单")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "status", value = "订单状态", dataType = "Integer"),
            @ApiImplicitParam(name = "offset", value = "偏移量", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "数量", dataType = "Integer")
    })
    @GetMapping("/page")
    public PageInfo<MallOrderVo> list(@RequestParam(value = "status",required = false) Integer status,
                                      @RequestParam(value = "offset",defaultValue = "0") Integer offset,
                                      @RequestParam(value = "limit",defaultValue = "10") Integer limit) {

        PageInfo<MallOrderVo> orderVoPageInfo = orderService.getVoPageByFilter(SecurityUserUtils.getUserId(), status, offset, limit);

        if (CollectionUtils.isNotEmpty(orderVoPageInfo.getList())) {
            orderVoPageInfo.getList().forEach(orderVo -> {
                List<MallOrderItemVo> orderItemVoList = orderItemService.getLimitVoListByOrderId(orderVo.getOrderId());
                orderVo.setOrderItemVoList(orderItemVoList);
            });
        }
        return orderVoPageInfo;
    }

    @ApiOperation("获取订单详情")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "orderId", value = "订单id", dataType = "Integer")
    })
    @GetMapping("/detail/{orderId}")
    public MallOrderVo detail(@PathVariable("orderId") @Min(1) @Max(2147483647) Integer orderId) {
        MallOrder order = orderService.selectByPrimaryKey(orderId);
        if (!order.getUserId().equals(SecurityUserUtils.getUserId())) {
            throw new MallException(MallError.MallErrorEnum.INTERNAL_SYSTEM_ERROR);
        }

        MallOrderVo orderVo = orderService.getDetailVo(orderId);

        List<MallOrderItemVo> orderItemVoList = orderItemService.getVoByOrderId(orderVo.getOrderId());
        orderVo.setOrderItemVoList(orderItemVoList);
        return orderVo;
    }

    @ApiOperation("取消订单")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "orderId", value = "订单id", dataType = "Integer")
    })
    @PostMapping("/close/{orderId}")
    public void closeOrder(@PathVariable("orderId") @Min(1) @Max(2147483647) Integer orderId) {
        orderService.closeOrder(orderId, SecurityUserUtils.getUserId());

        // 删除订单缓存
        orderService.deleteCache(SecurityUserUtils.getUserId());
        // 删除用户优惠券缓存
        userCouponService.deleteCache(SecurityUserUtils.getUserId());
    }

    @ApiOperation("分页搜索订单")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "productName", value = "商品名称", dataType = "String"),
            @ApiImplicitParam(name = "offset", value = "偏移量", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "数量", dataType = "Integer")
    })
    @GetMapping("/search")
    public PageInfo<MallOrderVo> search(@RequestParam(value = "productName", defaultValue = "") String productName,
                                        @RequestParam(value = "offset",defaultValue = "0") Integer offset,
                                        @RequestParam(value = "limit",defaultValue = "10") Integer limit) {

        // 添加用户搜索历史
        userSearchHistoryService.insertOrUpdate(SecurityUserUtils.getUserId(), productName, MallUserSearchHistoryTypeEnums.ORDER.getValue());

        PageInfo<MallOrderVo> orderVoPageInfo = orderService.getVoByProductName(SecurityUserUtils.getUserId(), productName, offset, limit);

        if (CollectionUtils.isNotEmpty(orderVoPageInfo.getList())) {
            for (MallOrderVo orderVo : orderVoPageInfo.getList()) {
                List<MallOrderItemVo> orderItemVoList = orderItemService.getLimitVoListBySearch(orderVo.getOrderId(), productName);
                orderVo.setOrderItemVoList(orderItemVoList);
            }
        }
        return orderVoPageInfo;
    }

    @ApiOperation("评价订单")
    @PostMapping("/comment")
    public void comment(@RequestBody MallOrder order) {
        orderService.commentSuccess(order);

        // 删除订单缓存
        orderService.deleteCache(SecurityUserUtils.getUserId());
    }

}
