package com.shpun.mall.back.scheduler;

import com.shpun.mall.common.common.Const;
import com.shpun.mall.common.config.ProfileConfig;
import com.shpun.mall.common.enums.*;
import com.shpun.mall.common.model.MallDelivery;
import com.shpun.mall.common.model.MallDeliveryOrder;
import com.shpun.mall.common.model.MallOrder;
import com.shpun.mall.common.model.MallUserCoupon;
import com.shpun.mall.common.service.*;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @Description: 后台定时调度器
 * @Author: sun
 * @Date: 2020/9/9 13:37
 */
@Component
public class MallBackScheduler {

    @Autowired
    private ProfileConfig profileConfig;

    @Autowired
    private RedisService redisService;

    @Autowired
    private MallOrderService orderService;

    @Autowired
    private MallUserCouponService userCouponService;

    @Autowired
    private MallDeliveryService deliveryService;

    @Autowired
    private MallDeliveryOrderService deliveryOrderService;

    /**
     * 订单超时调度器，每分钟执行一次
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void orderTimeoutScheduler() {
        // 生产模式从redis的zset中获取
        if (Const.PROFILE_PROD.equals(profileConfig.getActiveProfile())) {
            StringBuilder keySb = new StringBuilder(Const.REDIS_KEY_PREFIX)
                    .append(Const.REDIS_KEY_DELIMITER)
                    .append(Const.REDIS_KEY_ORDER_TIMEOUT_ZSET);

            Set<Object> timeoutOrderSet = redisService.zRangeByScore(keySb.toString(), 0, new Date().getTime());
            if (CollectionUtils.isNotEmpty(timeoutOrderSet)) {
                timeoutOrderSet.forEach(obj -> {
                    String orderKey = (String) obj;
                    String[] orderKeys = orderKey.split(Const.REDIS_PARAM_DELIMITER);
                    Integer orderId = Integer.valueOf(orderKeys[1]);
                    Integer userId = Integer.valueOf(orderKeys[2]);

                    MallOrder order = orderService.selectByPrimaryKey(orderId);
                    // 未支付直接关闭订单再remove
                    if (MallOrderStatusEnums.WAIT2PAY.getValue().equals(order.getStatus())) {
                        orderService.closeOrder(orderId, userId);

                        // 删除订单缓存
                        orderService.deleteCache(userId);
                        // 删除用户优惠券缓存
                        userCouponService.deleteCache(userId);
                    }
                    redisService.zRemove(keySb.toString(), orderKey);
                });
            }

            // 开发模式从表中获取
        } else if (Const.PROFILE_DEV.equals(profileConfig.getActiveProfile())) {
            List<MallOrder> wait2PayOrderList = orderService.getList(MallOrderStatusEnums.WAIT2PAY.getValue(), SortEnums.ASC.getValue());
            if (CollectionUtils.isNotEmpty(wait2PayOrderList)) {
                wait2PayOrderList.forEach(order -> {
                    Date orderTime = order.getOrderTime();
                    Date endTime = new Date(orderTime.getTime() + Const.DEFAULT_ORDER_TIMEOUT);
                    if (endTime.before(new Date())) {
                        orderService.closeOrder(order.getOrderId(), order.getUserId());

                        // 删除订单缓存
                        orderService.deleteCache(order.getUserId());
                        // 删除用户优惠券缓存
                        userCouponService.deleteCache(order.getUserId());
                    }
                });
            }
        }
    }

    /**
     * 用户优惠券超时调度器，每天凌晨运行一次
     */
    @Scheduled(cron = "0 0 0 */1 * ?")
    public void userCouponTimeoutScheduler() {
        List<MallUserCoupon> unusedCouponList = userCouponService.getList(MallUserCouponStatusEnums.UNUSED.getValue());
        if (CollectionUtils.isNotEmpty(unusedCouponList)) {
            unusedCouponList.forEach(userCoupon -> {
                if (userCoupon.getEndTime().before(new Date())) {
                    userCoupon.setStatus(MallUserCouponStatusEnums.EXPIRED.getValue());
                    userCouponService.updateByPrimaryKeySelective(userCoupon);
                }
            });
        }
    }

    /**
     * 分配配送员调度器，每分钟执行一次
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void allocateDelivery() {
        // 生产模式从redis的zset中获取
        if (Const.PROFILE_PROD.equals(profileConfig.getActiveProfile())) {
            StringBuilder keySb = new StringBuilder(Const.REDIS_KEY_PREFIX)
                    .append(Const.REDIS_KEY_DELIMITER)
                    .append(Const.REDIS_KEY_WAIT_2_DELIVERY_ORDER_ZSET);

            Set<Object> wait2DeliveryOrderSet = redisService.zRangeByScore(keySb.toString(), 0, new Date().getTime());
            if (CollectionUtils.isNotEmpty(wait2DeliveryOrderSet)) {
                wait2DeliveryOrderSet.forEach(obj -> {
                    String orderKey = (String) obj;
                    String[] orderKeys = orderKey.split(Const.REDIS_PARAM_DELIMITER);
                    Integer orderId = Integer.valueOf(orderKeys[1]);

                    // 获取空闲的配送员，分配配送员
                    Integer deliveryId = deliveryService.getIdleDeliveryId();
                    if (deliveryId != null) {
                        MallDeliveryOrder deliveryOrder = new MallDeliveryOrder();
                        deliveryOrder.setDeliveryId(deliveryId);
                        deliveryOrder.setOrderId(orderId);
                        deliveryOrder.setStatus(MallDeliveryOrderStatusEnums.WAIT2DELIVERY.getValue());
                        deliveryOrderService.insertSelective(deliveryOrder);
                        redisService.zRemove(keySb.toString(), orderKey);
                    }
                });
            }

            // 开发模式从表中获取
        } else if (Const.PROFILE_DEV.equals(profileConfig.getActiveProfile())) {
            List<MallOrder> paidOrderList = orderService.getList(MallOrderStatusEnums.PAID.getValue(), SortEnums.ASC.getValue());
            if (CollectionUtils.isNotEmpty(paidOrderList)) {
                paidOrderList.forEach(order -> {
                    // 获取空闲的配送员，分配配送员
                    Integer deliveryId = deliveryService.getIdleDeliveryId();
                    if (deliveryId != null) {
                        MallDeliveryOrder deliveryOrder = new MallDeliveryOrder();
                        deliveryOrder.setDeliveryId(deliveryId);
                        deliveryOrder.setOrderId(order.getOrderId());
                        deliveryOrder.setStatus(MallDeliveryOrderStatusEnums.WAIT2DELIVERY.getValue());
                        deliveryOrderService.insertSelective(deliveryOrder);
                    }
                });
            }
        }
    }

    /**
     * 配送订单调度器，每分钟执行一次
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void toDelivery() {
        // 检查配送员的订单，大于等于5个，就去配送
        List<Integer> need2DeliveryIdList = deliveryService.getNeed2DeliveryIdList();
        if (CollectionUtils.isNotEmpty(need2DeliveryIdList)) {
            // 更新配送员信息，配送员订单信息，订单信息
            need2DeliveryIdList.forEach(deliveryId -> {
                MallDelivery delivery = new MallDelivery();
                delivery.setDeliveryId(deliveryId);
                delivery.setStatus(MallDeliveryStatusEnums.DELIVERING.getValue());
                deliveryService.updateByPrimaryKeySelective(delivery);

                // 获取该配送员配送的订单，将订单都更新为待收货
                List<MallDeliveryOrder> deliveryOrderList = deliveryOrderService.getListByFilter(deliveryId, MallDeliveryOrderStatusEnums.WAIT2DELIVERY.getValue());
                deliveryOrderList.forEach(deliveryOrder -> {
                    deliveryOrder.setStatus(MallDeliveryOrderStatusEnums.DELIVERING.getValue());
                    deliveryOrderService.updateByPrimaryKeySelective(deliveryOrder);

                    MallOrder order = new MallOrder();
                    order.setOrderId(deliveryOrder.getOrderId());
                    order.setStatus(MallOrderStatusEnums.WAIT2RECEIVE.getValue());
                    order.setDeliveryId(deliveryId);
                    order.setDeliveryTime(new Date());
                    orderService.updateByPrimaryKeySelective(order);
                });
            });
        }
    }

    /**
     * 配送成功调度器，每分钟执行一次
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void deliverySuccess() {
        // 检查配送员的订单，如果最早的订单配送时间超过30分钟，自动收货
        List<Integer> deliveringIdList = deliveryService.getDeliveringIdList();
        if (CollectionUtils.isNotEmpty(deliveringIdList)) {
            // 更新配送员信息，配送员订单信息，订单信息
            deliveringIdList.forEach(deliveryId -> {
                MallDelivery delivery = new MallDelivery();
                delivery.setDeliveryId(deliveryId);
                delivery.setStatus(MallDeliveryStatusEnums.WAIT2DELIVERY.getValue());
                deliveryService.updateByPrimaryKeySelective(delivery);

                // 获取该配送员配送的订单，将订单都更新为已收货
                List<MallDeliveryOrder> deliveryOrderList = deliveryOrderService.getListByFilter(deliveryId, MallDeliveryOrderStatusEnums.DELIVERING.getValue());
                deliveryOrderList.forEach(deliveryOrder -> {
                    deliveryOrder.setStatus(MallDeliveryOrderStatusEnums.COMPLETED.getValue());
                    deliveryOrder.setReceiveTime(new Date());
                    deliveryOrderService.updateByPrimaryKeySelective(deliveryOrder);

                    MallOrder order = new MallOrder();
                    order.setOrderId(deliveryOrder.getOrderId());
                    order.setStatus(MallOrderStatusEnums.WAIT2COMMENT.getValue());
                    order.setReceiveTime(new Date());
                    orderService.updateByPrimaryKeySelective(order);
                });
            });
        }
    }

}
