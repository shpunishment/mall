package com.shpun.mall.back;

import com.shpun.mall.common.model.MallOrder;
import com.shpun.mall.common.service.MallOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/22 11:10
 */
@SpringBootTest
public class MallOrderServiceTest {

    @Autowired
    private MallOrderService orderService;

    @Test
    public void calculatePrice() {
        List<Integer> cartIdList = Stream.of(4,5,6).collect(Collectors.toList());

        MallOrder order = orderService.calculatePrice(cartIdList, null);

        System.out.println(order);
    }

    @Test
    public void generateOrder() {
        MallOrder order = new MallOrder();
        order.setUserId(1);
        order.setAddressId(1);
        order.setRemark("订单备注");
        order.setExpectTime("尽快送达");
        order.setPayType(1);

        List<Integer> cartIdList = Stream.of(7,8,9).collect(Collectors.toList());

        orderService.generateOrder(order, cartIdList);
    }

    @Test
    public void getByUserId() {
        List<MallOrder> orderList = orderService.getByUserId(1);

        System.out.println();
    }

    /**
     * 取消订单
     */
    @Test
    public void closeOrder() {
        orderService.closeOrder(3, 1);
    }

    /**
     * 支付成功
     */
    @Test
    public void paySuccess() {
        orderService.paySuccess(8);
    }

    /**
     * 收货成功
     */
    @Test
    public void receiveSuccess() {
        orderService.receiveSuccess(8);
    }

    /**
     * 评价成功
     */
    @Test
    public void commentSuccess() {
        MallOrder order = new MallOrder();
        order.setOrderId(8);
        order.setScore(4);
        order.setComment("不错不错");
        orderService.commentSuccess(order);
    }

}
