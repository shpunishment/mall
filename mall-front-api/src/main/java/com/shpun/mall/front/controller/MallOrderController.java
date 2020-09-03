package com.shpun.mall.front.controller;

import com.github.pagehelper.PageInfo;
import com.shpun.mall.common.enums.MallUserSearchHistoryTypeEnums;
import com.shpun.mall.common.exception.MallException;
import com.shpun.mall.common.model.MallOrder;
import com.shpun.mall.common.model.vo.MallOrderItemVo;
import com.shpun.mall.common.model.vo.MallOrderVo;
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

import java.util.List;

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
    @PostMapping("/calculateByCoupon")
    public MallOrder calculateByCoupon(@RequestBody MallOrderVo orderVo) {
        return orderService.calculatePrice(orderVo.getCartIdList(), orderVo.getCouponId());
    }

    @ApiOperation("生成订单")
    @PostMapping("/generate")
    public void generateOrder(@RequestBody MallOrderVo orderVo) {
        MallOrder order = new MallOrder();
        BeanUtils.copyProperties(orderVo, order);
        order.setUserId(SecurityUserUtils.getUserId());
        orderService.generateOrder(order, orderVo.getCartIdList());

        // 删除商品缓存
        productService.deleteCache();
        // 删除购物车缓存
        cartService.deleteCache(SecurityUserUtils.getUserId());
        // 删除订单缓存
        orderService.deleteCache(SecurityUserUtils.getUserId());
        // 删除限时抢购缓存
        flashItemService.deleteCache();
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
    public MallOrderVo detail(@PathVariable("orderId") Integer orderId) {
        MallOrder order = orderService.selectByPrimaryKey(orderId);
        if (!order.getUserId().equals(SecurityUserUtils.getUserId())) {
            throw new MallException("系统内部错误！");
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
    public void closeOrder(@PathVariable("orderId") Integer orderId) {
        orderService.closeOrder(orderId, SecurityUserUtils.getUserId());

        // 删除订单缓存
        orderService.deleteCache(SecurityUserUtils.getUserId());
    }

    @ApiOperation("分页搜索订单")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "productName", value = "商品名称", dataType = "String"),
            @ApiImplicitParam(name = "offset", value = "偏移量", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "数量", dataType = "Integer")
    })
    @GetMapping("/search")
    public PageInfo<MallOrderVo> search(@RequestParam("productName") String productName,
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
