package com.shpun.mall.common.service;

import com.github.pagehelper.PageInfo;
import com.shpun.mall.common.model.MallCoupon;
import com.shpun.mall.common.model.vo.MallCouponVo;

import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/9/2 14:52
 */
public interface MallCouponService {

    void deleteByPrimaryKey(Integer couponId);

    void insertSelective(MallCoupon record);

    MallCoupon selectByPrimaryKey(Integer couponId);

    void updateByPrimaryKeySelective(MallCoupon record);

    void insertWithClassifyIdList(MallCoupon coupon, List<Integer> classifyIdList);

    void updateWithClassifyIdList(MallCoupon coupon, List<Integer> classifyIdList);

    void insertWithProductIdList(MallCoupon coupon, List<Integer> productIdList);

    void updateWithProductIdList(MallCoupon coupon, List<Integer> productIdList);

    List<MallCoupon> getList();

    /**
     * 获取启用的，通用类型的，优惠券
     * @return
     */
    List<MallCouponVo> getVoList();

    PageInfo<MallCouponVo> getVoPage(Integer offset, Integer limit);

    List<Integer> getClassifyIdList(Integer couponId);

    List<Integer> getProductIdList(Integer couponId);

    MallCoupon getAvailable(Integer couponId);

    MallCoupon lockCoupon(Integer couponId);

    void update(MallCoupon coupon);

    MallCoupon getForOrder(Integer couponId);

    /**
     * 优惠券被领取，删除缓存
     */
    void deleteCache();

    List<Integer> getNewUserCouponId();

}
