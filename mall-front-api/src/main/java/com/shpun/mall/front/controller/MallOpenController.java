package com.shpun.mall.front.controller;

import com.shpun.mall.common.enums.MallOrderStatusEnums;
import com.shpun.mall.common.model.MallOrder;
import com.shpun.mall.common.service.MallOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/9/9 15:57
 */
@Api(tags = "开放接口控制器")
@RequestMapping("/api/open")
@RestController
public class MallOpenController {

    @Autowired
    private MallOrderService orderService;

    @ApiModelProperty("支付成功，callback")
    @GetMapping("/pay/callback")
    public String payCallback(@RequestParam("out_trade_no") String orderNumber,
                              @RequestParam("trade_no") String payNumber,
                              @RequestParam("timestamp") Date payTime) {
        MallOrder order = orderService.getByFilter(orderNumber, MallOrderStatusEnums.WAIT2PAY.getValue());
        if (order != null) {
            orderService.paySuccess(order.getOrderId(), payNumber, payTime);

            // 删除订单缓存
            orderService.deleteCache(order.getUserId());
        }
        return "redirect: /";
    }

    @ApiModelProperty("支付中断，callback")
    @GetMapping("/pay/quit")
    public String quitCallback() {
        return "redirect: /";
    }

}
