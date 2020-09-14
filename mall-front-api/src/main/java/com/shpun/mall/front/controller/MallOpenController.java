package com.shpun.mall.front.controller;

import com.shpun.mall.common.enums.MallOrderStatusEnums;
import com.shpun.mall.common.model.MallOrder;
import com.shpun.mall.common.service.MallOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @Value("${mall.ip:}")
    private String ip;

    @Value("${server.port:}")
    private String port;

    @Value("${server.servlet.context-path:}")
    private String contextPath;

    @ApiModelProperty("支付成功，callback")
    @GetMapping("/pay/callback")
    public ModelAndView payCallback(@RequestParam("out_trade_no") String orderNumber,
                                    @RequestParam("trade_no") String payNumber,
                                    @RequestParam("timestamp") String payTimeStr) throws ParseException {
        MallOrder order = orderService.getByFilter(orderNumber, MallOrderStatusEnums.WAIT2PAY.getValue());
        if (order != null) {
            SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date payTime = datetimeFormat.parse(payTimeStr);
            orderService.paySuccess(order.getOrderId(), payNumber, payTime);

            // 删除订单缓存
            orderService.deleteCache(order.getUserId());
        }

        StringBuilder redirectUrlSb = new StringBuilder("http://")
                .append(ip)
                .append(":")
                .append(port)
                .append(contextPath);
        return new ModelAndView("redirect:" + redirectUrlSb.toString());
    }

    @ApiModelProperty("支付中断，callback")
    @GetMapping("/pay/quit")
    public ModelAndView quitCallback() {
        StringBuilder redirectUrlSb = new StringBuilder("http://")
                .append(ip)
                .append(":")
                .append(port)
                .append(contextPath);
        return new ModelAndView("redirect:" + redirectUrlSb.toString());
    }

}
