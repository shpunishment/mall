package com.shpun.mall.common.service;

import com.github.pagehelper.PageInfo;
import com.shpun.mall.common.model.MallUserCoupon;
import com.shpun.mall.common.model.vo.MallCouponVo;
import com.shpun.mall.common.model.vo.MallUserCouponVo;

import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/9/2 14:53
 */
public interface MallUserCouponService {

    void deleteByPrimaryKey(Integer id);

    void insertSelective(MallUserCoupon record);

    MallUserCoupon selectByPrimaryKey(Integer id);

    void updateByPrimaryKeySelective(MallUserCoupon record);

    List<MallCouponVo> getCouponVoListByFilter(Integer userId, Integer status);

    /**
     * 适配前端修改
     * @param userId
     * @param status
     * @param offset
     * @param limit
     * @return
     */
    PageInfo<MallCouponVo> getCouponVoPageByFilter(Integer userId, Integer status, Integer offset, Integer limit);

    List<MallUserCouponVo> getVoListByFilter(Integer userId, Integer status);

    PageInfo<MallUserCouponVo> getVoPageByFilter(Integer userId, Integer status, Integer offset, Integer limit);

    /**
     * 根据couponIdList获取用户已领取的couponId
     * @param userId
     * @param couponIdList
     * @return
     */
    List<Integer> getReceivedCouponId(Integer userId, List<Integer> couponIdList);

    MallUserCoupon getByUserIdAndCouponId(Integer userId, Integer couponId);

    Integer getTodayUseCount(Integer userId);

    MallUserCoupon canUse(Integer userId, Integer couponId);

    List<MallUserCouponVo> getAvailableVoList(Integer userId);

    List<MallUserCoupon> getList(Integer status);

    Integer getAvailableCount(Integer userId);

    List<MallUserCoupon> getUnusedExpiredList();

    /**
     * 领取优惠券，下单成功，根据用户id删除缓存
     * @param userId
     */
    void deleteCache(Integer userId);

}
