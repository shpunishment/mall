package com.shpun.mall.back.scheduler;

import com.shpun.mall.common.common.Const;
import com.shpun.mall.common.config.ProfileConfig;
import com.shpun.mall.common.enums.MallOrderStatusEnums;
import com.shpun.mall.common.enums.MallUserCouponStatusEnums;
import com.shpun.mall.common.model.MallOrder;
import com.shpun.mall.common.model.MallUserCoupon;
import com.shpun.mall.common.service.MallOrderService;
import com.shpun.mall.common.service.MallUserCouponService;
import com.shpun.mall.common.service.RedisService;
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

    /**
     * 订单超时调度器，每分钟执行一次
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void orderTimeoutScheduler() {
        // 生成模式从redis的zset中获取
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
            List<MallOrder> wait2PayOrderList = orderService.getList(MallOrderStatusEnums.WAIT2PAY.getValue());
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

}
