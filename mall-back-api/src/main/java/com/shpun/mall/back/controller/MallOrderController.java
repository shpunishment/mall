package com.shpun.mall.back.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shpun.mall.common.model.MallOrder;
import com.shpun.mall.common.service.MallOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/29 13:50
 */
@Api(tags = "订单控制器")
@RequestMapping("/api/order")
@RestController
public class MallOrderController {

    @Autowired
    private MallOrderService orderService;

    @ApiOperation("分页获取订单")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "status", value = "订单状态", dataType = "Integer"),
            @ApiImplicitParam(name = "orderTimeSort", value = "订单时间排序，1顺序，2逆序", dataType = "Integer"),
            @ApiImplicitParam(name = "offset", value = "偏移量", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "数量", dataType = "Integer")
    })
    @GetMapping("/page")
    public PageInfo<MallOrder> page(@RequestParam(value = "status") Integer status,
                                    @RequestParam(value = "orderTimeSort", defaultValue = "2") Integer orderTimeSort,
                                    @RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                    @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        PageHelper.offsetPage(offset, limit);
        PageInfo<MallOrder> orderPageInfo = new PageInfo<>(orderService.getList(status, orderTimeSort));
        return orderPageInfo;
    }

    @ApiOperation("收货成功")
    @GetMapping("/receive/{orderId}")
    public void receiveSuccess(@PathVariable("orderId") Integer orderId) {
        orderService.receiveSuccess(orderId);
    }

}
