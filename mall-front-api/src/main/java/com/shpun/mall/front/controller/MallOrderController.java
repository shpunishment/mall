package com.shpun.mall.front.controller;

import com.github.pagehelper.PageInfo;
import com.shpun.mall.common.common.Const;
import com.shpun.mall.common.config.ProfileConfig;
import com.shpun.mall.common.enums.MallOrderStatusEnums;
import com.shpun.mall.common.enums.MallUserSearchHistoryTypeEnums;
import com.shpun.mall.common.exception.MallError;
import com.shpun.mall.common.exception.MallException;
import com.shpun.mall.common.model.MallDelivery;
import com.shpun.mall.common.model.MallOrder;
import com.shpun.mall.common.model.vo.MallCouponVo;
import com.shpun.mall.common.model.vo.MallOrderItemVo;
import com.shpun.mall.common.model.vo.MallOrderVo;
import com.shpun.mall.common.model.vo.MallUserCouponVo;
import com.shpun.mall.common.service.*;
import com.shpun.mall.front.security.SecurityUserUtils;
import io.swagger.annotations.*;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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

    @Autowired
    private ProfileConfig profileConfig;

    @Autowired
    private MallDeliveryService deliveryService;

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
    public Map<String, List<MallCouponVo>> calculateWithCoupon(@RequestParam("cartIdList") List<Integer> cartIdList) {
        return orderService.calculateWithCouponVo(SecurityUserUtils.getUserId(), cartIdList);
    }

    @ApiOperation("生成订单")
    @PostMapping("/generate")
    public void generateOrder(@RequestBody @Validated(MallOrderVo.Generate.class) MallOrderVo orderVo, HttpServletResponse response) {
        MallOrder order = new MallOrder();
        BeanUtils.copyProperties(orderVo, order);
        order.setUserId(SecurityUserUtils.getUserId());
        orderService.generateOrder(order, orderVo.getCartIdList());

        if (order.getOrderId() != null) {
            // 支付订单
            orderService.payOrder(order, response);
            if (Const.PROFILE_PROD.equals(profileConfig.getActiveProfile())) {
                // 添加订单超时延迟队列
                orderService.zAddOrderTimeout(SecurityUserUtils.getUserId(), order.getOrderId(), order.getOrderTime());
            }
        }

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
            @ApiImplicitParam(name = "status", value = "订单状态，不填全部，0待付款，2待收货，3待评价", dataType = "Integer"),
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
                // 价格改为文本
                orderVo.setTotalPriceStr(orderVo.getTotalPrice().toString());

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

        // 待支付订单，返回订单结束时间
        if (MallOrderStatusEnums.WAIT2PAY.getValue().equals(orderVo.getStatus())) {
            orderVo.setEndTime(new Date(orderVo.getOrderTime().getTime() + Const.DEFAULT_ORDER_TIMEOUT));
        }
        // 价格改为文本
        orderVo.setProductPriceStr(orderVo.getProductPrice().toString());
        orderVo.setDeliveryPriceStr(orderVo.getDeliveryPrice().toString());
        if (orderVo.getCouponPrice() != null) {
            orderVo.setCouponPriceStr(orderVo.getCouponPrice().toString());
        }
        // 添加配送员
        if (orderVo.getDeliveryId() != null) {
            MallDelivery delivery = deliveryService.selectByPrimaryKey(orderVo.getDeliveryId());
            orderVo.setDeliveryMan(delivery.getName());
        }
        orderVo.setTotalPriceStr(orderVo.getTotalPrice().toString());
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

    @ApiModelProperty("支付订单")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "orderId", value = "订单id", dataType = "String"),
            @ApiImplicitParam(name = "payType", value = "支付方式，1支付宝，2微信", dataType = "Integer")
    })
    @GetMapping("/pay/{orderId}")
    public void payOrder(@PathVariable("orderId") @Min(0) @Max(2147483647) Integer orderId,
                         @RequestParam("payType") @Min(1) @Max(2) Integer payType,
                         HttpServletResponse response) {
        MallOrder order = orderService.selectByPrimaryKey(orderId);
        if (!SecurityUserUtils.getUserId().equals(order.getUserId())) {
            throw new MallException(MallError.MallErrorEnum.INTERNAL_SYSTEM_ERROR);
        }

        order.setPayType(payType);
        orderService.payOrder(order, response);
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
