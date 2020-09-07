package com.shpun.mall.back;

import com.shpun.mall.common.model.MallUserCoupon;
import com.shpun.mall.common.model.vo.MallUserCouponVo;
import com.shpun.mall.common.service.MallUserCouponService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/9/3 10:05
 */
@SpringBootTest
public class MallUserCouponServiceTest {

    @Autowired
    private MallUserCouponService userCouponService;

    @Test
    public void insert() {
        MallUserCoupon userCoupon1 = buildMallUserCoupon(1, 1);
        userCouponService.insertSelective(userCoupon1);

        MallUserCoupon userCoupon2 = buildMallUserCoupon(1, 7);
        userCouponService.insertSelective(userCoupon2);

        MallUserCoupon userCoupon3 = buildMallUserCoupon(1, 9);
        userCouponService.insertSelective(userCoupon3);
    }

    private MallUserCoupon buildMallUserCoupon(Integer userId, Integer couponId) {
        MallUserCoupon userCoupon = new MallUserCoupon();
        userCoupon.setUserId(userId);
        userCoupon.setCouponId(couponId);
        return userCoupon;
    }

    @Test
    public void getVoListByFilter() {
        List<MallUserCouponVo> voList = userCouponService.getVoListByFilter(1, 1);
        System.out.println();
    }

}
