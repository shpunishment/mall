package com.shpun.mall.back;

import com.shpun.mall.common.model.MallCoupon;
import com.shpun.mall.common.service.MallCouponService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/9/2 15:07
 */
@SpringBootTest
public class MallCouponServiceTest {

    @Autowired
    private MallCouponService couponService;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Test
    public void insert() throws Exception {
        // 通用
        MallCoupon coupon1 = buildMallCoupon("全品类通用券1", "全品类通用券（全场，除限时抢购商品）", new BigDecimal("10.00"), new BigDecimal("2.00"),
                10, 1,1,1,0, null, null);
        couponService.insertSelective(coupon1);

        MallCoupon coupon2 = buildMallCoupon("全品类通用券2", "全品类通用券（全场，除限时抢购商品）", new BigDecimal("30.00"), new BigDecimal("5.00"),
                10, 1,1,1,4, null, null);
        couponService.insertSelective(coupon2);

        MallCoupon coupon3 = buildMallCoupon("全品类通用券3", "全品类通用券（全场，除限时抢购商品）", new BigDecimal("50.00"), new BigDecimal("10.00"),
                10, 1,1,2,null, dateFormat.parse("2020-09-03 00:00:00"), dateFormat.parse("2020-09-07 23:59:59"));
        couponService.insertSelective(coupon3);

        // 新人
        MallCoupon coupon4 = buildMallCoupon("全品类新人礼券1", "全品类新人礼券（全场，除限时抢购商品）", new BigDecimal("20.00"), new BigDecimal("10.00"),
                null, 2,1,1,7, null, null);
        couponService.insertSelective(coupon4);

        MallCoupon coupon5 = buildMallCoupon("全品类新人礼券2", "全品类新人礼券（全场，除限时抢购商品）", new BigDecimal("30.00"), new BigDecimal("10.00"),
                null, 2,1,1,7, null, null);
        couponService.insertSelective(coupon5);

        MallCoupon coupon6 = buildMallCoupon("全品类新人礼券3", "全品类新人礼券（全场，除限时抢购商品）", new BigDecimal("40.00"), new BigDecimal("10.00"),
                null, 2,1,1,7, null, null);
        couponService.insertSelective(coupon6);

    }

    @Test
    public void insertWithClassifyIdList() throws Exception {
        List<Integer> classifyIdList = Stream.of(4,5,6,7,8).collect(Collectors.toList());

        // 通用
        MallCoupon coupon1 = buildMallCoupon("水果类通用券1", "水果类通用券", new BigDecimal("20.00"), new BigDecimal("5.00"),
                10, 1,2,2,null, dateFormat.parse("2020-09-03 00:00:00"), dateFormat.parse("2020-09-15 23:59:59"));
        couponService.insertWithClassifyIdList(coupon1, classifyIdList);

        // 新人
        MallCoupon coupon2 = buildMallCoupon("水果类新人礼券1", "水果类新人礼券", new BigDecimal("20.00"), new BigDecimal("5.00"),
                null, 2,2,1,7, null, null);
        couponService.insertWithClassifyIdList(coupon2, classifyIdList);
    }

    @Test
    public void insertWithProductIdList() throws Exception {
        List<Integer> productIdList = Stream.of(3,4).collect(Collectors.toList());

        // 通用
        MallCoupon coupon1 = buildMallCoupon("XX芒果专享券1", "XX芒果专享券", new BigDecimal("20.00"), new BigDecimal("2.00"),
                10, 1,3,2, null, dateFormat.parse("2020-09-03 00:00:00"), dateFormat.parse("2020-09-15 23:59:59"));
        couponService.insertWithProductIdList(coupon1, productIdList);

        // 新人
        MallCoupon coupon2 = buildMallCoupon("XX芒果新人礼券1", "XX芒果新人礼券", new BigDecimal("20.00"), new BigDecimal("2.00"),
                null, 2,3,1,7, null, null);
        couponService.insertWithProductIdList(coupon2, productIdList);
    }

    private MallCoupon buildMallCoupon(String name, String desc, BigDecimal minPrice, BigDecimal discount, Integer total,
                                       Integer type, Integer userType, Integer timeType, Integer days, Date startTime, Date endTime) {
        MallCoupon coupon = new MallCoupon();
        coupon.setName(name);
        coupon.setDesc(desc);
        coupon.setMinPrice(minPrice);
        coupon.setDiscount(discount);
        coupon.setTotal(total);
        coupon.setType(type);
        coupon.setUseType(userType);
        coupon.setTimeType(timeType);
        coupon.setDays(days);
        coupon.setStartTime(startTime);
        coupon.setEndTime(endTime);
        return coupon;
    }

}
