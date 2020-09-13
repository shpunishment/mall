package com.shpun.mall.back.scheduler;

import com.shpun.mall.common.common.Const;
import com.shpun.mall.common.config.ProfileConfig;
import com.shpun.mall.common.enums.*;
import com.shpun.mall.common.model.MallDeliveryOrder;
import com.shpun.mall.common.model.MallOrder;
import com.shpun.mall.common.model.MallUserCoupon;
import com.shpun.mall.common.service.*;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description: 后台定时调度器
 * @Author: sun
 * @Date: 2020/9/9 13:37
 */
@Component
@EnableScheduling
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
     * 订单超时调度器，每三十秒执行一次
     */
    @Scheduled(cron = "0/30 * * * * ?")
    public void orderTimeoutScheduler() {
        // 生产模式从redis的zset中获取
        if (Const.PROFILE_PROD.equals(profileConfig.getActiveProfile())) {
            StringBuilder keySb = new StringBuilder(Const.REDIS_KEY_PREFIX)
                    .append(Const.REDIS_KEY_DELIMITER)
                    .append(Const.REDIS_KEY_ORDER_TIMEOUT_ZSET);

            // 获取支付时间已超时的订单
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
        List<MallUserCoupon> unusedCouponList = userCouponService.getUnusedExpiredList();
        if (CollectionUtils.isNotEmpty(unusedCouponList)) {
            unusedCouponList.forEach(userCoupon -> {
                userCoupon.setStatus(MallUserCouponStatusEnums.EXPIRED.getValue());
                userCouponService.updateByPrimaryKeySelective(userCoupon);
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
                    .append(Const.REDIS_KEY_ALLOCATE_DELIVERY_ZSET);

            Set<Object> wait2DeliveryOrderSet = redisService.zRangeByScore(keySb.toString(), 0, new Date().getTime());
            if (CollectionUtils.isNotEmpty(wait2DeliveryOrderSet)) {
                for (Object obj : wait2DeliveryOrderSet) {
                    String orderKey = (String) obj;
                    String[] orderKeys = orderKey.split(Const.REDIS_PARAM_DELIMITER);
                    Integer orderId = Integer.valueOf(orderKeys[1]);

                    // 先获取空闲的，待配送订单数量最少的配送员
                    Integer deliveryId = deliveryService.getIdleDeliveryId(MallDeliveryStatusEnums.WAIT2DELIVERY.getValue());
                    if (deliveryId == null) {
                        // 没有空闲配送员，获取配送中订单数量最少的配送员
                        deliveryId = deliveryService.getIdleDeliveryId(MallDeliveryStatusEnums.DELIVERING.getValue());
                    }

                    if (deliveryId != null) {
                        MallDeliveryOrder deliveryOrder = new MallDeliveryOrder();
                        deliveryOrder.setDeliveryId(deliveryId);
                        deliveryOrder.setOrderId(orderId);
                        deliveryOrder.setStatus(MallDeliveryOrderStatusEnums.WAIT2DELIVERY.getValue());
                        deliveryOrderService.insertSelective(deliveryOrder);
                        redisService.zRemove(keySb.toString(), orderKey);
                    }
                }
            }

            // 开发模式从表中获取
        } else if (Const.PROFILE_DEV.equals(profileConfig.getActiveProfile())) {
            List<MallOrder> paidOrderList = orderService.getList(MallOrderStatusEnums.PAID.getValue(), SortEnums.ASC.getValue());
            if (CollectionUtils.isNotEmpty(paidOrderList)) {
                for (MallOrder order : paidOrderList) {
                    // 检查订单是否已分配
                    if (deliveryOrderService.getByOrderId(order.getOrderId()) != null) {
                        continue;
                    }

                    // 先获取空闲的，待配送订单数量最少的配送员
                    Integer deliveryId = deliveryService.getIdleDeliveryId(MallDeliveryStatusEnums.WAIT2DELIVERY.getValue());
                    if (deliveryId == null) {
                        // 没有空闲配送员，获取配送中订单数量最少的配送员
                        deliveryId = deliveryService.getIdleDeliveryId(MallDeliveryStatusEnums.DELIVERING.getValue());
                    }

                    if (deliveryId != null) {
                        MallDeliveryOrder deliveryOrder = new MallDeliveryOrder();
                        deliveryOrder.setDeliveryId(deliveryId);
                        deliveryOrder.setOrderId(order.getOrderId());
                        deliveryOrder.setStatus(MallDeliveryOrderStatusEnums.WAIT2DELIVERY.getValue());
                        deliveryOrderService.insertSelective(deliveryOrder);
                    }
                }
            }
        }
    }

    /**
     * 配送订单调度器，每分钟执行一次
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void toDelivery() {
        // 检查配送员的订单，大于等于1个，就去配送
        List<Integer> need2DeliveryIdList = deliveryService.getNeed2DeliveryIdList();
        if (CollectionUtils.isNotEmpty(need2DeliveryIdList)) {
            // 更新配送员信息，配送员订单信息，订单信息
            need2DeliveryIdList.forEach(deliveryId -> {
                List<Integer> orderIdList = deliveryService.wait2Receive(deliveryId);

                if (CollectionUtils.isNotEmpty(orderIdList)) {
                    List<Integer> userIdList = orderService.getUserIdListByOrderIdList(orderIdList);
                    Set<Integer> userIdSet = new HashSet<>(userIdList);
                    userIdSet.forEach(userId -> {
                        // 删除订单缓存
                        orderService.deleteCache(userId);
                    });
                }
            });
        }
    }

    /**
     * 配送成功调度器，每分钟执行一次
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void deliverySuccess() {
        // 获取配送中的配送员，将订单自动收货
        List<Integer> deliveringIdList = deliveryService.getDeliveringIdList();
        if (CollectionUtils.isNotEmpty(deliveringIdList)) {
            // 更新配送员信息，配送员订单信息，订单信息
            deliveringIdList.forEach(deliveryId -> {
                List<Integer> orderIdList = deliveryService.receiveSuccess(deliveryId);

                if (CollectionUtils.isNotEmpty(orderIdList)) {
                    List<Integer> userIdList = orderService.getUserIdListByOrderIdList(orderIdList);
                    Set<Integer> userIdSet = new HashSet<>(userIdList);
                    userIdSet.forEach(userId -> {
                        // 删除订单缓存
                        orderService.deleteCache(userId);
                    });
                }
            });
        }
    }

}
