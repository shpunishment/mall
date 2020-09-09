package com.shpun.mall.back.test;

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

    /**
     * 测试
     * @throws Exception
     */
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
                10, 1,1,2,null, dateFormat.parse("2020-09-07 00:00:00"), dateFormat.parse("2020-09-20 23:59:59"));
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
                10, 1,2,2,null, dateFormat.parse("2020-09-07 00:00:00"), dateFormat.parse("2020-09-15 23:59:59"));
        couponService.insertWithClassifyIdList(coupon1, classifyIdList);

        // 新人
        MallCoupon coupon2 = buildMallCoupon("水果类新人礼券1", "水果类新人礼券", new BigDecimal("20.00"), new BigDecimal("5.00"),
                null, 2,2,1,7, null, null);
        couponService.insertWithClassifyIdList(coupon2, classifyIdList);
    }

    @Test
    public void insertWithProductIdList() throws Exception {
        List<Integer> productIdList = Stream.of(3).collect(Collectors.toList());

        // 通用
        MallCoupon coupon1 = buildMallCoupon("XX芒果专享券1", "XX芒果专享券", new BigDecimal("20.00"), new BigDecimal("2.00"),
                10, 1,3,2, null, dateFormat.parse("2020-09-07 00:00:00"), dateFormat.parse("2020-09-15 23:59:59"));
        couponService.insertWithProductIdList(coupon1, productIdList);

        // 新人
        MallCoupon coupon2 = buildMallCoupon("XX芒果新人礼券1", "XX芒果新人礼券", new BigDecimal("20.00"), new BigDecimal("2.00"),
                null, 2,3,1,7, null, null);
        couponService.insertWithProductIdList(coupon2, productIdList);
    }

    /**
     * 造数据
     * @throws Exception
     */
    @Test
    public void insert2() throws Exception {
        // 通用券
        MallCoupon coupon1 = buildMallCoupon("全品类通用券", "全品类通用券（全场，除限时抢购商品）", new BigDecimal("10.00"), new BigDecimal("2.00"),
                10, 1,1,1,0, null, null);
        couponService.insertSelective(coupon1);

        MallCoupon coupon2 = buildMallCoupon("全品类通用券", "全品类通用券（全场，除限时抢购商品）", new BigDecimal("30.00"), new BigDecimal("5.00"),
                10, 1,1,1,null, dateFormat.parse("2020-09-09 00:00:00"), dateFormat.parse("2020-09-30 23:59:59"));
        couponService.insertSelective(coupon2);

        MallCoupon coupon3 = buildMallCoupon("全品类通用券", "全品类通用券（全场，除限时抢购商品）", new BigDecimal("50.00"), new BigDecimal("10.00"),
                10, 1,1,2,null, dateFormat.parse("2020-09-09 00:00:00"), dateFormat.parse("2020-09-30 23:59:59"));
        couponService.insertSelective(coupon3);

        // 新人
        MallCoupon coupon4 = buildMallCoupon("全品类新人礼券", "全品类新人礼券（全场，除限时抢购商品）", new BigDecimal("20.00"), new BigDecimal("10.00"),
                null, 2,1,1,7, null, null);
        couponService.insertSelective(coupon4);
    }

    @Test
    public void insertWithClassifyIdList2() throws Exception {
        List<Integer> classifyIdList;

        // 通用
        classifyIdList = Stream.of(11,12,13,14,15).collect(Collectors.toList());
        MallCoupon coupon1 = buildMallCoupon("水果类通用券", "水果类通用券（水果类，除限时抢购商品）", new BigDecimal("20.00"), new BigDecimal("5.00"),
                50, 1,2,2,null, dateFormat.parse("2020-09-07 00:00:00"), dateFormat.parse("2020-09-30 23:59:59"));
        couponService.insertWithClassifyIdList(coupon1, classifyIdList);

        classifyIdList = Stream.of(16,17,18,19,20,21,22,23).collect(Collectors.toList());
        MallCoupon coupon2 = buildMallCoupon("蔬菜类通用券", "蔬菜类通用券（蔬菜类，除限时抢购商品）", new BigDecimal("30.00"), new BigDecimal("5.00"),
                50, 1,2,2,null, dateFormat.parse("2020-09-07 00:00:00"), dateFormat.parse("2020-09-30 23:59:59"));
        couponService.insertWithClassifyIdList(coupon2, classifyIdList);

        classifyIdList = Stream.of(24,25,26,27,28,29,30,31).collect(Collectors.toList());
        MallCoupon coupon3 = buildMallCoupon("肉禽蛋类通用券", "肉禽蛋类通用券（肉禽蛋类，除限时抢购商品）", new BigDecimal("40.00"), new BigDecimal("5.00"),
                50, 1,2,2,null, dateFormat.parse("2020-09-07 00:00:00"), dateFormat.parse("2020-09-30 23:59:59"));
        couponService.insertWithClassifyIdList(coupon3, classifyIdList);

        classifyIdList = Stream.of(32,33,34,35,36).collect(Collectors.toList());
        MallCoupon coupon4 = buildMallCoupon("海鲜水产类通用券", "海鲜水产类通用券（海鲜水产类，除限时抢购商品）", new BigDecimal("50.00"), new BigDecimal("10.00"),
                50, 1,2,2,null, dateFormat.parse("2020-09-07 00:00:00"), dateFormat.parse("2020-09-30 23:59:59"));
        couponService.insertWithClassifyIdList(coupon4, classifyIdList);

        classifyIdList = Stream.of(37,38,39,40,41,42,43).collect(Collectors.toList());
        MallCoupon coupon5 = buildMallCoupon("粮油调味类通用券", "粮油调味类通用券（粮油调味类，除限时抢购商品）", new BigDecimal("30.00"), new BigDecimal("2.00"),
                50, 1,2,2,null, dateFormat.parse("2020-09-07 00:00:00"), dateFormat.parse("2020-09-30 23:59:59"));
        couponService.insertWithClassifyIdList(coupon5, classifyIdList);

        classifyIdList = Stream.of(44,45,46,47,48,49,50,51).collect(Collectors.toList());
        MallCoupon coupon6 = buildMallCoupon("熟食卤味类通用券", "熟食卤味类通用券（熟食卤味类，除限时抢购商品）", new BigDecimal("30.00"), new BigDecimal("6.00"),
                50, 1,2,2,null, dateFormat.parse("2020-09-07 00:00:00"), dateFormat.parse("2020-09-30 23:59:59"));
        couponService.insertWithClassifyIdList(coupon6, classifyIdList);

        classifyIdList = Stream.of(52,53,54,55,56,57,58,59).collect(Collectors.toList());
        MallCoupon coupon7 = buildMallCoupon("冰品面点类通用券", "冰品面点类通用券（冰品面点类，除限时抢购商品）", new BigDecimal("60.00"), new BigDecimal("6.00"),
                50, 1,2,2,null, dateFormat.parse("2020-09-07 00:00:00"), dateFormat.parse("2020-09-30 23:59:59"));
        couponService.insertWithClassifyIdList(coupon7, classifyIdList);

        classifyIdList = Stream.of(60,61,62,63,64,65,66,67).collect(Collectors.toList());
        MallCoupon coupon8 = buildMallCoupon("牛奶面包类通用券", "牛奶面包类通用券（牛奶面包类，除限时抢购商品）", new BigDecimal("39.00"), new BigDecimal("9.00"),
                50, 1,2,2,null, dateFormat.parse("2020-09-07 00:00:00"), dateFormat.parse("2020-09-30 23:59:59"));
        couponService.insertWithClassifyIdList(coupon8, classifyIdList);

        classifyIdList = Stream.of(68,69,70,71,72,73,74,75).collect(Collectors.toList());
        MallCoupon coupon9 = buildMallCoupon("酒水冲饮类通用券", "酒水冲饮类通用券（酒水冲饮类，除限时抢购商品）", new BigDecimal("40.00"), new BigDecimal("5.00"),
                50, 1,2,2,null, dateFormat.parse("2020-09-07 00:00:00"), dateFormat.parse("2020-09-30 23:59:59"));
        couponService.insertWithClassifyIdList(coupon9, classifyIdList);

        classifyIdList = Stream.of(76,77,78,79,80,81,82,83).collect(Collectors.toList());
        MallCoupon coupon10 = buildMallCoupon("休闲零食类通用券", "休闲零食类通用券（休闲零食类，除限时抢购商品）", new BigDecimal("30.00"), new BigDecimal("5.00"),
                50, 1,2,2,null, dateFormat.parse("2020-09-07 00:00:00"), dateFormat.parse("2020-09-30 23:59:59"));
        couponService.insertWithClassifyIdList(coupon10, classifyIdList);

        // 新人
        classifyIdList = Stream.of(11,12,13,14,15).collect(Collectors.toList());
        MallCoupon coupon11 = buildMallCoupon("水果类新人礼券", "水果类新人礼券（水果类，除限时抢购商品）", new BigDecimal("20.00"), new BigDecimal("5.00"),
                null, 2,2,1,7, null, null);
        couponService.insertWithClassifyIdList(coupon11, classifyIdList);

        classifyIdList = Stream.of(16,17,18,19,20,21,22,23).collect(Collectors.toList());
        MallCoupon coupon12 = buildMallCoupon("蔬菜类新人礼券", "蔬菜类新人礼券（蔬菜类，除限时抢购商品）", new BigDecimal("20.00"), new BigDecimal("5.00"),
                null, 2,2,1,7, null, null);
        couponService.insertWithClassifyIdList(coupon12, classifyIdList);

        classifyIdList = Stream.of(24,25,26,27,28,29,30,31).collect(Collectors.toList());
        MallCoupon coupon13 = buildMallCoupon("肉禽蛋类新人礼券", "肉禽蛋类新人礼券（肉禽蛋类，除限时抢购商品）", new BigDecimal("40.00"), new BigDecimal("10.00"),
                null, 2,2,1,7, null, null);
        couponService.insertWithClassifyIdList(coupon13, classifyIdList);

    }

    // todo 先添加商品
    @Test
    public void insertWithProductIdList2() throws Exception {
        List<Integer> productIdList = Stream.of(3).collect(Collectors.toList());

        // 通用
        MallCoupon coupon1 = buildMallCoupon("XX芒果专享券1", "XX芒果专享券", new BigDecimal("20.00"), new BigDecimal("2.00"),
                10, 1,3,2, null, dateFormat.parse("2020-09-07 00:00:00"), dateFormat.parse("2020-09-15 23:59:59"));
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
