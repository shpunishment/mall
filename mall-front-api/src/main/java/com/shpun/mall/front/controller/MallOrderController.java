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
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
@Validated
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
        return orderService.calculatePrice(SecurityUserUtils.getUserId(), cartIdList);
    }

    @ApiOperation("根据用户优惠券计算价格")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "cartIdList", value = "购物车idList", dataType = "List<Integer>")
    })
    @PostMapping("/calculateWithCoupon")
    public Map<String, List<MallUserCouponVo>> calculateWithCoupon(@RequestParam("cartIdList") List<Integer> cartIdList) {
        return orderService.calculateWithCoupon(SecurityUserUtils.getUserId(), cartIdList);
    }

    @ApiOperation("生成订单")
    @PostMapping("/generate")
    public void generateOrder(@RequestBody @Validated(MallOrderVo.Generate.class) MallOrderVo orderVo) {
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
    public PageInfo<MallOrderVo> list(@RequestParam(value = "status",required = false) @Min(-1) @Max(4) Integer status,
                                      @RequestParam(value = "offset",defaultValue = "0") @Min(0) @Max(2147483647) Integer offset,
                                      @RequestParam(value = "limit",defaultValue = "10") @Min(1) @Max(2147483647) Integer limit) {

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
        MallOrderVo orderVo = orderService.getDetailVo(SecurityUserUtils.getUserId(), orderId);
        if (orderVo == null) {
            throw new MallException(MallError.MallErrorEnum.INTERNAL_SYSTEM_ERROR);
        }

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
    public PageInfo<MallOrderVo> search(@RequestParam(value = "productName", defaultValue = "") @Length(max = 20) String productName,
                                        @RequestParam(value = "offset",defaultValue = "0") @Min(0) @Max(2147483647) Integer offset,
                                        @RequestParam(value = "limit",defaultValue = "10") @Min(1) @Max(2147483647) Integer limit) {

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
    public void comment(@RequestBody @Validated(MallOrder.Comment.class) MallOrder order) {
        // todo 校验：订单是否属于该用户
        orderService.commentSuccess(order);

        // 删除订单缓存
        orderService.deleteCache(SecurityUserUtils.getUserId());
    }

}
