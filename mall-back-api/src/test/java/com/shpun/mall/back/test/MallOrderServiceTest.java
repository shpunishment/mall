package com.shpun.mall.back.test;

import com.shpun.mall.common.model.MallOrder;
import com.shpun.mall.common.model.vo.MallUserCouponVo;
import com.shpun.mall.common.service.MallOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
        List<Integer> cartIdList = Stream.of(1,2,3).collect(Collectors.toList());

        MallOrder order = orderService.calculatePrice(1, cartIdList);

        System.out.println(order);
    }

    @Test
    public void calculateWithCoupon() {
        List<Integer> cartIdList = Stream.of(1,2,3).collect(Collectors.toList());

        Map<String, List<MallUserCouponVo>> map = orderService.calculateWithUserCouponVo(1, cartIdList);

        System.out.println(map);
    }

    @Test
    public void generateOrder() {
        MallOrder order = new MallOrder();
        order.setUserId(1);
        order.setCouponId(7);
        order.setAddressId(1);
        order.setRemark("订单备注");
        order.setExpectTime("尽快送达");
        order.setPayType(1);

        List<Integer> cartIdList = Stream.of(1,2,3).collect(Collectors.toList());

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
        orderService.paySuccess(10, "15998171107121002", new Date());
        orderService.paySuccess(11, "15998171266661003", new Date());
        orderService.paySuccess(12, "15998171287321004", new Date());
        orderService.paySuccess(13, "15998171307821005", new Date());
        orderService.paySuccess(14, "15998171327661006", new Date());
        orderService.paySuccess(15, "15998171348841007", new Date());
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

    @Test
    public void generateOrderNumber() {
        List<String> orderNumberList = Collections.synchronizedList(new ArrayList<>());
        IntStream.range(0,100000).parallel().forEach(i->{
            orderNumberList.add(generate());
        });

        List<String> filterOrderNumberList = orderNumberList.stream().distinct().collect(Collectors.toList());

        System.out.println("生成的订单数：" + orderNumberList.size());
        System.out.println("过滤重复后的订单数：" + filterOrderNumberList.size());
        System.out.println("重复的订单数：" + (orderNumberList.size() - filterOrderNumberList.size()));
    }

    private static final AtomicInteger ORDER_SEQ = new AtomicInteger(1000);
    private String generate() {
        if(ORDER_SEQ.intValue() > 9990){
            ORDER_SEQ.getAndSet(1000);
        }
        return new Date().getTime() + "" + ORDER_SEQ.getAndIncrement();
    }

}
